package com.nhnacademy.nhnmartcustomerservice.service.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;
import com.nhnacademy.nhnmartcustomerservice.repository.AnswerRepository;
import com.nhnacademy.nhnmartcustomerservice.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public boolean isExistsAnswer(long inquiryId) {
        return answerRepository.isExistsAnswer(inquiryId);
    }

    @Override
    public void registerAnswer(long inquiryId, Answer answer) {
        if(answerRepository.isExistsAnswer(inquiryId)) {
            return;
        }

        answerRepository.registerAnswer(inquiryId, answer);
    }

    @Override
    public void deleteAnswer(long inquiryId) {
        if(!answerRepository.isExistsAnswer(inquiryId)) {
            return;
        }

        answerRepository.deleteAnswer(inquiryId);
    }

    @Override
    public void updateAnswer(long inquiryId, Answer answer) {
        if(!answerRepository.isExistsAnswer(inquiryId)) {
            return;
        }

        answerRepository.updateAnswer(inquiryId, answer);
    }

    @Override
    public Answer getAnswerByInquiryId(long inquiryId) {
        if(!answerRepository.isExistsAnswer(inquiryId)) {
            return null;
        }

        return answerRepository.getAnswerByInquiryId(inquiryId);
    }
}
