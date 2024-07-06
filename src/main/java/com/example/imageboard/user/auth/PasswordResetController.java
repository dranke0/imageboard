package com.example.imageboard.auth;

import com.example.imageboard.user.User;
import com.example.imageboard.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        // Validate token, find user by token, update password
        User user = userService.findByResetToken(token);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            user.setResetToken(null); // clear the reset token
            userService.save(user);
        }
        return "redirect:/login";
    }*/
}
