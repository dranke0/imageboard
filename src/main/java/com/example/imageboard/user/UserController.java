package com.example.imageboard.user;

import com.example.imageboard.entity.EntityController;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    public UserController(@Qualifier("userService") UserService userService,
                          @Qualifier("userMapper") UserMapper userMapper) {
        super(userService);
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam String query) {
        // ... (rest of the code is the same as before)
    }

    @PostMapping("/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) { // Add @Valid
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data");
        }
        return userService.save(userDto);
    }
}


