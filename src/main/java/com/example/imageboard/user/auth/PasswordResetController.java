package com.example.imageboard.user.auth;

import com.example.imageboard.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    private PasswordResetService passwordResetService;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/reset-password")
    public String showResetForm(@RequestParam String token) {
        // Validate token (e.g., check if token is in the database)
        return "reset-password";
    }

   /* @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @ModelAttribute("password") String password) {
        // Validate token, find userEntity by token, update password
        User userEntity = userService.findByResetToken(token);
        if (userEntity != null) {
            userEntity.setPassword(passwordEncoder.encode(password));
            userEntity.setResetToken(null); // clear the reset token
            userService.save(userEntity);
        }
        return "redirect:/login";
    }*/
}
