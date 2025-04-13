package com.nhnacademy.nhnmartcustomerservice.validator;

import com.nhnacademy.nhnmartcustomerservice.domain.request.IdCategoryRequest;
import com.nhnacademy.nhnmartcustomerservice.exception.NotFoundCategoryException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class IdCategoryRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return IdCategoryRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "user ID is empty");

        IdCategoryRequest idCategoryRequest = (IdCategoryRequest) target;
        String category = idCategoryRequest.getCategory();
        if(Objects.nonNull(category) && !category.equals("전체보기") && !category.equals("불만접수") && !category.equals("제안") && !category.equals("환불/교환") && !category.equals("칭찬해요") && !category.equals("기타문의")) {
            errors.rejectValue("category", "", "category is not matching!");
        }

    }
}
