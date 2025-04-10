package com.nhnacademy.nhnmartcustomerservice.service.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.repository.InquiryRepository;
import com.nhnacademy.nhnmartcustomerservice.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InquiryServiceImpl implements InquiryService {
    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public boolean isExistsInquiries(String id) {
        return inquiryRepository.isExistsInquiries(id);
    }

    @Override
    public boolean isExistsInquiry(String id, String inquiryId) {
        ArrayList<Inquiry> inquiries = getInquiries(id);

        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId().equals(inquiryId)) return true;
        }

        return false;
    }

    @Override
    public ArrayList<Inquiry> getInquiries(String id) {
        if(!inquiryRepository.isExistsInquiries(id)) {
            return null;
        }

        return inquiryRepository.getInquiries(id);
    }

    @Override
    public Inquiry getInquiry(String id, String inquiryId) {
        if(!inquiryRepository.isExistsInquiries(id)) {
            return null;
        }

        if(inquiryRepository.getInquiry(id, inquiryId) == null) {
            return null;
        }

        return inquiryRepository.getInquiry(id, inquiryId);
    }

    @Override
    public void registerInquiry(String id, Inquiry inquiry) {
        if(isExistsInquiry(id, inquiry.getInquiryId())) return;

        inquiryRepository.registerInquiry(id, inquiry);
    }

    @Override
    public void deleteInquiry(String id, String inquiryId) {
        if(!inquiryRepository.isExistsInquiries(id) || !isExistsInquiry(id, inquiryId)) return;

        inquiryRepository.deleteInquiry(id, inquiryId);
    }

    @Override
    public void updateInquiry(String id, Inquiry inquiry) {
        if(!inquiryRepository.isExistsInquiries(id) || !isExistsInquiry(id, inquiry.getInquiryId())) return;

        inquiryRepository.updateInquiry(id, inquiry);
    }
}
