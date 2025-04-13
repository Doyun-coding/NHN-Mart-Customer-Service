package com.nhnacademy.nhnmartcustomerservice.controller.admin;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundUserException;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import com.nhnacademy.nhnmartcustomerservice.validator.IdCategoryRequestValidator;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class CsAdminControllerTest {

    @Mock
    private IdCategoryRequestValidator idCategoryRequestValidator;

    @Mock
    private UserService userService;

    @Mock
    private InquiryService inquiryService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new CsAdminController(idCategoryRequestValidator, userService, inquiryService)).build();
    }

    @Test
    @DisplayName("CsAdminController getUser Method Test")
    void csAdminControllerGetUserMethodTest() throws Exception {

        // Given
        User admin = new User("admin", "1234", "admin", Auth.ADMIN);

        when(userService.getUser("admin")).thenReturn(admin);

        // When
        mockMvc.perform(get("/cs/admin")
                .param("id", "admin")
                .param("category", "전체보기"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("admin", admin));

        // Then

    }

    @Test
    @DisplayName("CsAdminController csAdminController Method Admin is Null Test")
    void csAdminControllerCsAdminControllerMethodAdminIsNullTest() throws Exception {

        // Given
        User admin = new User("admin", "1234", "admin", Auth.ADMIN);

        when(userService.getUser("admin")).thenReturn(null);

        // When
        Assertions.assertThrows(ServletException.class, ()->{
            mockMvc.perform(get("/cs/admin")
                    .param("id", "admin")
                    .param("category", "전체보기"));
        });

        // Then

    }

    @Test
    @DisplayName("CsAdminController csAdminController Method Test")
    void csAdminControllerMethodTest() throws Exception {

        // Given
        User admin = new User("admin", "1234", "admin", Auth.ADMIN);

        when(userService.getUser("admin")).thenReturn(admin);

        // When
        mockMvc.perform(get("/cs/admin")
                .param("id", "admin")
                .param("category", "전체보기"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/csAdminForm"));

        // Then

    }


}