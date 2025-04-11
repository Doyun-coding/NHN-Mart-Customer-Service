package com.nhnacademy.nhnmartcustomerservice.repository;

import com.nhnacademy.nhnmartcustomerservice.domain.Answer;

public interface AnswerRepository {

    boolean isExistsAnswer(long inquiryId);

    void registerAnswer(long inquiryId, Answer answer);
    void deleteAnswer(long inquiryId);
    void updateAnswer(long inquiryId, Answer answer);

    Answer getAnswerByInquiryId(long inquiryId);

}
