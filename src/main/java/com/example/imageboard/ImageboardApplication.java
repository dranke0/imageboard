package com.example.imageboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ImageboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageboardApplication.class, args);
    }

}
