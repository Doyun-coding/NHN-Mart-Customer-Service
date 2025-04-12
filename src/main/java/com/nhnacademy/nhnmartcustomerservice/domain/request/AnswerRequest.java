package com.nhnacademy.nhnmartcustomerservice.domain.request;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AnswerRequest {

    long inquiryId;

    @Size(min = 1, max = 40000)
    String answerContent;

}
