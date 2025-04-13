package com.nhnacademy.nhnmartcustomerservice.controller.user;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;
import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.service.AnswerService;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdInquiryIdRequestValidator;
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
class InquiryDetailControllerTest {

    @Mock
    private IdInquiryIdRequestValidator idInquiryIdRequestValidator;

    @Mock
    private InquiryService inquiryService;

    @Mock
    private AnswerService answerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new InquiryDetailController(idInquiryIdRequestValidator, inquiryService, answerService)).build();
    }

    @Test
    @DisplayName("InquiryDetailController inquiryDetail Method Answered False Test")
    void inquiryDetailControllerInquiryDetailMethodAnsweredFalseTest() throws Exception {

        // Given
        List<String> list = new ArrayList<>();
        list.add("path");

        Inquiry inquiry = new Inquiry("nhn1", 1, "hello", "전체보기", "hi", LocalDateTime.now(), "admin", list, false);

        when(inquiryService.getInquiry("nhn1", 1)).thenReturn(inquiry);

        // When
        mockMvc.perform(get("/cs/inquiryDetail")
                .param("id", "nhn1")
                .param("inquiryId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/inquiryDetailForm"));

        // Then

    }

    @Test
    @DisplayName("InquiryDetailController inquiryDetail Method Answered True Test")
    void inquiryDetailControllerInquiryDetailMethodAnsweredTrueTest() throws Exception {

        // Given
        List<String> list = new ArrayList<>();
        list.add("path");
        Inquiry inquiry = new Inquiry("nhn1", 1, "hello", "전체보기", "hi", LocalDateTime.now(), "admin", list, true);
        Answer answer = new Answer(1, "good", LocalDateTime.now(), "admin");

        when(inquiryService.getInquiry("nhn1", 1)).thenReturn(inquiry);
        when(answerService.getAnswerByInquiryId(1)).thenReturn(answer);

        // When
        mockMvc.perform(get("/cs/inquiryDetail")
                .param("id", "nhn1")
                .param("inquiryId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/inquiryDetailForm"));

        // Then

    }

    @Test
    @DisplayName("InquiryDetailController Validator Test")
    void inquiryDetailControllerValidatorTest() throws Exception {

        // Given

        // When
        Assertions.assertThrows(ServletException.class, ()->{
            mockMvc.perform(get("/cs/inquiryDetail")
                    .param("id", "nhn1")
                    .param("inquiryId", "-1"));
        });

        // Then

    }

}