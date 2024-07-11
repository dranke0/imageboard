package com.example.imageboard.user;

import com.example.imageboard.user.dto.AuthenticatedUserDto;
import com.example.imageboard.user.dto.PublicUserDto;
import com.example.imageboard.user.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    public ResponseEntity<AuthenticatedUserDto> createUser(@Valid @RequestBody AuthenticatedUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult);
        }
        AuthenticatedUserDto createdUserDto = userService.createUser(userDto, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('ADMIN')") // Example authorization check
    public ResponseEntity<AuthenticatedUserDto> updateUser(@PathVariable Long id, @Valid @RequestBody AuthenticatedUserDto userDto) {
        AuthenticatedUserDto updatedUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('ADMIN')") // Example authorization check
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
