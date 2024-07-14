package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ForumController.class) // Focus on web layer testing
class ForumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Mock the service layer dependency
    private ForumService forumService;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization/deserialization

    // Test for GET /api/forums/{id}
    @Test
    void testGetForumById() throws Exception {
        Long forumId = 1L;
        ForumDto mockForumDto = new ForumDto(/* ... initialize with test data ... */);

        when(forumService.get(forumId)).thenReturn(mockForumDto);

        mockMvc.perform(get("/api/forums/{id}", forumId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockForumDto)));
    }


    // Tests for POST, PUT, DELETE, etc.
}
