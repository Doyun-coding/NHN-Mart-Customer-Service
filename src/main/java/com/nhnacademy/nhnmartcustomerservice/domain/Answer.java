package com.nhnacademy.nhnmartcustomerservice.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Answer {

    long inquiryId;

    String answerContext;
    LocalDateTime answerCreatedTime;
    String answerAdminName;

}
