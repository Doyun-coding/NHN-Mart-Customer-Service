package com.nhnacademy.nhnmartcustomerservice.repository;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {

    boolean isExistsInquiries(String id);

    List<Inquiry> getInquiries(String id);
    Inquiry getInquiry(String id, long inquiryId);
    Inquiry getInquiryByInquiryId(long inquiryId);

    void registerInquiry(String id, Inquiry inquiry);
    void deleteInquiry(String id, long inquiryId);
    void updateInquiry(String id, Inquiry inquiry);

    List<Inquiry> getNotAnsweredInquiries();
    List<Inquiry> getInquiryByInquiryIdCategory(String id, String category);
    List<Inquiry> getNotAnsweredInquiresByCategory(String category);

}
