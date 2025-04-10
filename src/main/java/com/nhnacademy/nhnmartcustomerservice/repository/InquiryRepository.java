package com.nhnacademy.nhnmartcustomerservice.repository;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;

import java.util.ArrayList;
import java.util.List;

public interface InquiryRepository {

    boolean isExistsInquiries(String id);

    ArrayList<Inquiry> getInquiries(String id);
    Inquiry getInquiry(String id, String inquiryId);

    void registerInquiry(String id, Inquiry inquiry);
    void deleteInquiry(String id, String inquiryId);
    void updateInquiry(String id, Inquiry inquiry);

}
