package com.nhnacademy.nhnmartcustomerservice.service;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;

public interface AnswerService {

    boolean isExistsAnswer(long inquiryId);

    void registerAnswer(long inquiryId, Answer answer);
    void deleteAnswer(long inquiryId);
    void updateAnswer(long inquiryId, Answer answer);

    Answer getAnswerByInquiryId(long inquiryId);

}
