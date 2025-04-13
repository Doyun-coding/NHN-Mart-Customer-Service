package com.nhnacademy.nhnmartcustomerservice.validator;

import com.nhnacademy.nhnmartcustomerservice.domain.request.AnswerRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AnswerRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AnswerRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inquiryId", "", "inquiry is empty");

        AnswerRequest answerRequest = (AnswerRequest) target;
        String answerContent = answerRequest.getAnswerContent();
        if(answerContent.length() < 1 || answerContent.length() > 40000) {
            errors.rejectValue("answerContent", "", "Answer Content length 1~40000");
        }

    }
}
