package com.nhnacademy.nhnmartcustomerservice.validator;

import com.nhnacademy.nhnmartcustomerservice.domain.request.IdInquiryIdRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class IdInquiryIdRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return IdInquiryIdRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "ID is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inquiryId", "", "Inquiry ID is Empty");
    }
}
