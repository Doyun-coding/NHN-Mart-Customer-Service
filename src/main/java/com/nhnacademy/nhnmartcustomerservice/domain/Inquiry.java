package com.nhnacademy.nhnmartcustomerservice.domain;

import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Value
public class Inquiry {

    String id;
    long inquiryId;

    String title;
    String category;
    String content;
    LocalDateTime createdTime;
    String writer;
    List<String> filePath;

    boolean answered;

}
