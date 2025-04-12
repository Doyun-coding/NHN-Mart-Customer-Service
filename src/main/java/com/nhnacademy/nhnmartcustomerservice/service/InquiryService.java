package com.nhnacademy.nhnmartcustomerservice.service;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;

import java.util.List;

public interface InquiryService {

    boolean isExistsInquiries(String id);
    boolean isExistsInquiry(String id, long inquiryId);

    List<Inquiry> getInquiries(String id);
    Inquiry getInquiry(String id, long inquiryId);
    Inquiry getInquiryByInquiryId(long inquiryId);

    void registerInquiry(String id, Inquiry inquiry);
    void deleteInquiry(String id, long inquiryId);
    void updateInquiry(String id, Inquiry inquiry);

    List<Inquiry> getNotAnsweredInquiries();

}
