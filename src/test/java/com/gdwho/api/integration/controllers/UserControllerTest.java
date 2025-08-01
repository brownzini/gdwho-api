package com.gdwho.api.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        UserDBEntity user1 = new UserDBEntity(
                "john", "encryptedPass", RoleEnum.USER, Instant.now(),
                List.of(), List.of());
        UserDBEntity user2 = new UserDBEntity(
                "admin", "encryptedAdmin", RoleEnum.ADMIN, Instant.now(),
                List.of(), List.of());
        userRepository.saveAll(List.of(user1, user2));
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(get("/v1/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").exists())
                .andExpect(jsonPath("$[0].password").exists())
                .andExpect(jsonPath("$[0].role").exists())
                .andExpect(jsonPath("$[0].createdAt").exists())
                .andExpect(jsonPath("$[0].dataList").exists())
                .andExpect(jsonPath("$[0].entries").exists());
    }
}
