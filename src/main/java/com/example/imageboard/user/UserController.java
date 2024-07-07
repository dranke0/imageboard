package com.example.imageboard.user;

import com.example.imageboard.entity.EntityController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends EntityController<User, Long, UserDto> {

    private final UserService userService;
    private final UserMapper userMapper;
    public UserController(@Qualifier("userService") UserService userService,
                          @Qualifier("userMapper") UserMapper userMapper) {
        super(userService);
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping("/search") // Example of a custom search endpoint
    public List<UserDto> searchUsers(@RequestParam String query) {
        // Call the userService to perform the search
        List<User> users = userService.findAllDtos();
        return userMapper.mapEntitiesToDtos(users);
    }

    @PostMapping("/register") // Example of a registration endpoint
    public UserDto registerUser(@RequestBody UserDto userDto) {
        // Call the userService to register the user
        return userService.save(userDto);
    }
}


