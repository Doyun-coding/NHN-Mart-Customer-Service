package com.nhnacademy.nhnmartcustomerservice.repository.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;
import com.nhnacademy.nhnmartcustomerservice.repository.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnswerRepositoryImpl implements AnswerRepository {
    private static final Map<Long, Answer> repository = new HashMap<>();

    @Override
    public boolean isExistsAnswer(long inquiryId) {
        return repository.containsKey(inquiryId);
    }

    @Override
    public void registerAnswer(long inquiryId, Answer answer) {
       repository.put(inquiryId, answer);
    }

    @Override
    public void deleteAnswer(long inquiryId) {
        repository.remove(inquiryId);
    }

    @Override
    public void updateAnswer(long inquiryId, Answer answer) {
        repository.replace(inquiryId, answer);
    }

    @Override
    public Answer getAnswerByInquiryId(long inquiryId) {
        return repository.get(inquiryId);
    }

}
