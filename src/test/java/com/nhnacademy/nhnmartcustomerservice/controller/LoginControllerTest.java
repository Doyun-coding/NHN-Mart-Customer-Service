package com.nhnacademy.nhnmartcustomerservice.controller;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.exception.NotMatchesIdPasswordException;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.UserRequestValidator;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {LoginController.class})
@ContextConfiguration(classes = {UserService.class, UserRequestValidator.class})
class LoginControllerTest {

    @Mock
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private LoginController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Login Controller getLoginForm Method Test")
    void getLoginTest() throws Exception {

        // Given

        // When
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));

        // Then

    }

    @Test
    @DisplayName("Login Controller post Login Form Method User Login Success Test")
    void postLoginFormMethodTest() throws Exception {

        // Given
        String id = "nhn1";
        String password = "1234";
        User currentUser = new User(id, password, "nhn1", Auth.USER);

        when(userService.isMatches(id, password)).thenReturn(true);
        when(userService.getUser(id)).thenReturn(currentUser);

        // When
        mockMvc.perform(post("/login")
                .param("id", id)
                .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs?id=" + id));

        // Then

    }

    @Test
    @DisplayName("Login Controller post Login Form Method Admin Login Success Test")
    void postLoginFormMethodAdminLoginSuccessTest() throws Exception {

        // Given
        String id = "admin";
        String password = "1234";
        User currentUser = new User(id, password, "admin", Auth.ADMIN);

        when(userService.isMatches(id, password)).thenReturn(true);
        when(userService.getUser(id)).thenReturn(currentUser);

        // When
        mockMvc.perform(post("/login")
                .param("id", id)
                .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin?id=" + id));

        // Then

    }

    @Test
    @DisplayName("Login Controller post Login Method Not Matches Test")
    void postLoginFormMethodNotMatchesTest() throws Exception {

        // Given
        String id = "hello";
        String password = "1234";
        User currentUser = new User(id, password, "hello", Auth.USER);

        when(userService.isMatches(id, password)).thenReturn(false);

        // When
        Assertions.assertThrows(ServletException.class, ()->{
            mockMvc.perform(post("/login")
                    .param("id", id)
                    .param("password", password));
        });

        // Then

    }

}