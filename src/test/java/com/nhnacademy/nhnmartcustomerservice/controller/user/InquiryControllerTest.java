package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.InquiryRequestValidator;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class InquiryControllerTest {

    @Mock
    private InquiryRequestValidator inquiryRequestValidator;

    @Mock
    private UserService userService;

    @Mock
    private InquiryService inquiryService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new InquiryController(inquiryRequestValidator, userService, inquiryService)).build();
    }

    @Test
    @DisplayName("InquiryController getUser Method ID is Empty Test")
    void inquiryControllerGetUserMethodIDisEmptyTest() throws Exception {

        // Given

        // When
        Assertions.assertThrows(ServletException.class, ()->{
            mockMvc.perform(get("/cs/inquiry")
                    .param("id", ""));
        });

        // Then

    }

    @Test
    @DisplayName("InquiryController getUser Method Test")
    void inquiryControllerGetUserMethodTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(get("/cs/inquiry")
                .param("id", "nhn1")
                .param("category", "전체보기"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user));

        // Then

    }

    @Test
    @DisplayName("InquiryController Inquiry Method Test")
    void inquiryControllerInquiryMethodTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(get("/cs/inquiry")
                .param("id", "nhn1")
                .param("category", "전체보기"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/inquiryForm"));

        // Then

    }

    @Test
    @DisplayName("InquiryController PostInquiry Method Test")
    void inquiryControllerPostInquiryMethodTest() throws Exception {

        // Given
        User user = new User("nhn1", "1234", "nhn1", Auth.USER);
        MockMultipartFile file = new MockMultipartFile(
                "files",
                "image.jpg",
                "image/jpeg",
                "image-content".getBytes()
        );

        when(userService.getUser("nhn1")).thenReturn(user);

        // When
        mockMvc.perform(multipart("/cs/inquiry")
                        .file(file)
                        .param("id", "nhn1")
                .param("title", "hello")
                .param("category", "전체보기")
                        .param("content", "hi"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs?id=nhn1"));

        // Then

    }

}