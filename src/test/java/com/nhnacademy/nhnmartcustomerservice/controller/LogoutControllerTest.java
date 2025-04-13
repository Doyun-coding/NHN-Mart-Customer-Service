package com.nhnacademy.nhnmartcustomerservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {LogoutController.class})
@ContextConfiguration(classes = {LogoutController.class})
class LogoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Logout Controller postLogout Method logout Test")
    void postLogoutControllerPostLogoutMethodLogoutTest() throws Exception {

        // Given
        String id = "nhn1";

        // When
        mockMvc.perform(post("/logout")
                        .param("id", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // Then

    }

}