package com.nhnacademy.nhnmartcustomerservice.repository.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.repository.InquiryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InquiryRepositoryImpl implements InquiryRepository {
    private static final Map<String, ArrayList<Inquiry>> repository = new HashMap<>();

    @Override
    public boolean isExistsInquiries(String id) {
        return repository.containsKey(id);
    }

    @Override
    public ArrayList<Inquiry> getInquiries(String id) {
        return repository.get(id);
    }

    @Override
    public Inquiry getInquiry(String id, String inquiryId) {
        ArrayList<Inquiry> inquiries = repository.get(id);

        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId().equals(inquiryId)) {
                return inquiries.get(i);
            }
        }

        return null;
    }

    @Override
    public void registerInquiry(String id, Inquiry inquiry) {
        if(!isExistsInquiries(id)) {
            ArrayList<Inquiry> inquiries = new ArrayList<>();
            repository.put(id, inquiries);
        }

        ArrayList<Inquiry> inquiries = repository.get(id);
        inquiries.add(inquiry);
    }

    @Override
    public void deleteInquiry(String id, String inquiryId) {
        ArrayList<Inquiry> inquiries = repository.get(id);
        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId().equals(inquiryId)) {
                inquiries.remove(i);
                break;
            }
        }

    }

    @Override
    public void updateInquiry(String id, Inquiry inquiry) {
        ArrayList<Inquiry> inquiries = repository.get(id);
        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId().equals(inquiry.getInquiryId())) {
                inquiries.set(i, inquiry);
                break;
            }
        }
    }
}
