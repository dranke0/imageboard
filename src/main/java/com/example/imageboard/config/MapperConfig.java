package com.example.imageboard.config;

import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.thread.mapper.ThreadMapper;
import com.example.imageboard.user.mapper.AuthenticatedUserMapper;
import com.example.imageboard.user.mapper.PublicUserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CommentMapper commentMapper() {
        return Mappers.getMapper(CommentMapper.class);
    }

    @Bean
    public ThreadMapper threadMapper() {
        return Mappers.getMapper(ThreadMapper.class);
    }

    @Bean
    public ForumMapper forumMapper() {
        return Mappers.getMapper(ForumMapper.class);
    }

    @Bean
    public PublicUserMapper publicUserMapper() {
        return Mappers.getMapper(PublicUserMapper.class);
    }

    @Bean
    public AuthenticatedUserMapper authenticatedUserMapper() {
        return Mappers.getMapper(AuthenticatedUserMapper.class);
    }
}
