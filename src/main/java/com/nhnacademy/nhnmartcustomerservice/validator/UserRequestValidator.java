package com.nhnacademy.nhnmartcustomerservice.validator;

import com.nhnacademy.nhnmartcustomerservice.domain.request.UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "id is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "password is empty");
    }
}
