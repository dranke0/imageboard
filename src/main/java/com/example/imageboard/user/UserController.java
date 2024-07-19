package com.example.imageboard.user;

import com.example.imageboard.user.dto.AuthenticatedUserDto;
import com.example.imageboard.user.dto.PublicUserDto;
import com.example.imageboard.user.exception.InvalidUserException;
import com.example.imageboard.user.exception.ErrorResponse; // Assuming you have this class
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PublicUserDto>> getAllUsers() {
        List<PublicUserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicUserDto> getUserById(@PathVariable Long id) {
        PublicUserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<AuthenticatedUserDto> createUser(@Valid @RequestBody AuthenticatedUserDto userDto, BindingResult bindingResult) throws InvalidUserException {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult);
        }
        AuthenticatedUserDto createdUserDto = userService.createUser(userDto, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('ADMIN')")
    public ResponseEntity<AuthenticatedUserDto> updateUser(@PathVariable Long id, @Valid @RequestBody AuthenticatedUserDto userDto, BindingResult bindingResult) throws InvalidUserException {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult);
        }
        AuthenticatedUserDto updatedUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Exception Handler for InvalidUserException
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserException(InvalidUserException ex) {
        List<InvalidUserException.CustomFieldError> errors = ex.getCustomFieldErrors();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid user input", errors, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
