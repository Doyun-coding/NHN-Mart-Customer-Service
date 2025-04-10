package com.nhnacademy.nhnmartcustomerservice.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Inquiry {

    String inquiryId;

    String title;
    String category;
    String content;
    LocalDateTime createdTime;
    String writer;
    String filePath;
    boolean answered;

}
