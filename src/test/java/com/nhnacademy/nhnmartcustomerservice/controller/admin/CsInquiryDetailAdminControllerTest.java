package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.controller.user.InquiryDetailController;
import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import com.nhnacademy.nhnmartcustomerservice.service.AnswerService;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.AnswerRequestValidator;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class CsInquiryDetailAdminControllerTest {

    @Mock
    private AnswerRequestValidator answerRequestValidator;

    @Mock
    private UserService userService;

    @Mock
    private InquiryService inquiryService;

    @Mock
    private AnswerService answerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new CsInquiryDetailAdminController(answerRequestValidator, userService, inquiryService, answerService)).build();
    }

    @Test
    @DisplayName("CsInquiryDetailAdminController getInquiryIdRequest Method Validator Test")
    void csInquiryDetailAdminControllerGetInquiryIdRequestMethodValidatorTest() throws Exception {

        // Given

        // When
        Assertions.assertAll(()->{
            Assertions.assertThrows(ServletException.class, ()->{
                mockMvc.perform(get("/cs/admin/answer")
                        .param("id", "admin")
                        .param("inquiryId", "-1"));
            });
            Assertions.assertThrows(ServletException.class, ()->{
                mockMvc.perform(get("/cs/admin/answer")
                        .param("id", "")
                        .param("inquiryId", "1"));
            });
        });

        // Then

    }

    @Test
    @DisplayName("CsInquiryDetailAdminController inquiryDetail Method Test")
    void csInquiryDetailAdminControllerInquiryDetailMethodTest() throws Exception {

        // Given
        List<String> list = new ArrayList<>();
        list.add("path");

        Inquiry inquiry = new Inquiry("nhn1", 1, "hello", "전체보기", "hi", LocalDateTime.now(), "admin", list, false);

        when(inquiryService.getInquiryByInquiryId(1)).thenReturn(inquiry);

        // When
        mockMvc.perform(get("/cs/admin/answer")
                .param("id", "admin")
                .param("inquiryId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/inquiryDetailAdmin"));

        // Then

    }

    @Test
    @DisplayName("CsInquiryDetailAdminController PostInquiryDetail Method Test")
    void csInquiryDetailAdminControllerPostInquiryDetailMethodTest() throws Exception {

        // Given
        List<String> list = new ArrayList<>();
        list.add("path");

        Inquiry inquiry = new Inquiry("nhn1", 1, "hello", "전체보기", "hi", LocalDateTime.now(), "admin", list, false);

        when(inquiryService.getInquiryByInquiryId(1)).thenReturn(inquiry);

        User admin = new User("admin", "1234", "admin", Auth.ADMIN);
        when(userService.getUser("admin")).thenReturn(admin);


        // When
        mockMvc.perform(multipart("/cs/admin/answer")
                .param("id", "admin")
                .param("inquiryId", "1")
                        .param("inquiryId", "1")
                        .param("answerContent", "hello"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin?id=admin"));

        // Then

    }

}