package com.nhnacademy.nhnmartcustomerservice.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Answer {

    long inquiryId;

    String answerContent;
    LocalDateTime answerCreatedTime;
    String answerAdminName;

}
