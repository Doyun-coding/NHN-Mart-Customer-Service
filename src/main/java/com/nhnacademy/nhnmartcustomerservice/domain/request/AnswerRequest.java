package com.nhnacademy.nhnmartcustomerservice.domain.request;

import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.Value;

@Value
public class AnswerRequest {

    @NonNull
    long inquiryId;

    @Size(min = 1, max = 40000)
    String answerContent;

}
