package com.nhnacademy.nhnmartcustomerservice.domain;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class Inquiry {

    long inquiryId;

    String title;
    String category;
    String content;
    LocalDateTime createdTime;
    String writer;
    List<String> filePath;
    boolean answered;

}
