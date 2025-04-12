package com.nhnacademy.nhnmartcustomerservice.domain.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Value
public class InquiryRequest {

    @NonNull
    @Size(min = 2, max = 200)
    String title;

    String category;

    @NonNull
    @Size(min = 0, max = 40000)
    String content;

    List<MultipartFile> files;

}
