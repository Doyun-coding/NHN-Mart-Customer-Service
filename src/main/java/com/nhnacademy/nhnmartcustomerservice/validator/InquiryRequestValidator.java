package com.nhnacademy.nhnmartcustomerservice.validator;

import com.nhnacademy.nhnmartcustomerservice.domain.request.InquiryRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InquiryRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return InquiryRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "title is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "content is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "category is empty");

        InquiryRequest request = (InquiryRequest) target;
        String title = request.getTitle();
        if(title.length() < 2 || title.length() > 200) {
            errors.rejectValue("title", "", "title is 1~200");
        }

        String content = request.getContent();
        if(content.length() > 40000) {
            errors.rejectValue("content", "", "content is 0~40000");
        }

    }
}
