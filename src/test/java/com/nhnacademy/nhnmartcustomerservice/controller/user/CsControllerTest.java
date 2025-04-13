package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdCategoryRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class CsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private InquiryService inquiryService;

    @Mock
    private IdCategoryRequestValidator idCategoryRequestValidator;

    @Mock
    private CsController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new CsController(idCategoryRequestValidator, userService, inquiryService))
                .build();
    }

    @Test
    @DisplayName("User Cs Controller getUser Method User Test")
    void csControllerGetUserMethodNotFoundUserTest() throws Exception {
        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(get("/cs")
                .param("id", "nhn1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user));

        // Then

    }

    @Test
    @DisplayName("Cs Controller cs Method user is Null Test")
    void csControllerCsMethodUserIsNullTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(null);

        // When
        mockMvc.perform(get("/cs")
                .param("id", "nhn1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Then

    }

    @Test
    @DisplayName("Cs Controller cs Method Test")
    void csControllerCsMethodTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(get("/cs")
                .param("id", "nhn1")
                .param("category", "전체보기"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/csForm"));

        // Then

    }

    @Test
    @DisplayName("Cs Controller PostInquiryBtn Method user is Null Test")
    void csControllerPostInquiryBtnMethodUserIsNullTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(null);

        // When
        mockMvc.perform(post("/cs")
                .param("id", "nhn1")
                .param("category", "전체보기"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Then

    }

    @Test
    @DisplayName("Cs Controller postInquiryBtn Method Test")
    void csControllerPostInquiryBtnMethodTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(post("/cs")
                .param("id", "nhn1")
                .param("category", "전체보기"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/inquiry?id=nhn1"));

        // Then

    }


}