package com.example.imageboard.config;

import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.thread.mapper.ThreadMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.imageboard.user.UserRepository;

@Configuration
public class MapperConfig {

    @Bean
    public CommentMapper commentMapper(UserRepository userRepository) {
        return new CommentMapper(userRepository);
    }

    @Bean
    public ForumMapper forumMapper() {
        return new ForumMapper();
    }

    @Bean
    public ThreadMapper threadMapper(ForumRepository forumRepository) {
        return new ThreadMapper(forumRepository);
    }

    @Bean
    public AuthenticatedUserMapper authenticatedUserMapper() {
        return new AuthenticatedUserMapper();
    }

    @Bean
    public PublicUserMapper publicUserMapper() {
        return new PublicUserMapper();
    }
}
