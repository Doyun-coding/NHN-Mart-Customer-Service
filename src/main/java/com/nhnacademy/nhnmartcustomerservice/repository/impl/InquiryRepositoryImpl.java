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
    private static final Map<String, List<Inquiry>> repository = new HashMap<>();

    @Override
    public boolean isExistsInquiries(String id) {
        return repository.containsKey(id);
    }

    @Override
    public List<Inquiry> getInquiries(String id) {
        if(!isExistsInquiries(id)) {
            List<Inquiry> inquiries = new ArrayList<>();
            repository.put(id, inquiries);
        }

        return repository.get(id);
    }

    @Override
    public Inquiry getInquiry(String id, long inquiryId) {
        List<Inquiry> inquiries = repository.get(id);

        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId() == inquiryId) {
                return inquiries.get(i);
            }
        }

        return null;
    }

    @Override
    public void registerInquiry(String id, Inquiry inquiry) {
        if(!isExistsInquiries(id)) {
            List<Inquiry> inquiries = new ArrayList<>();
            repository.put(id, inquiries);
        }

        List<Inquiry> inquiries = repository.get(id);
        inquiries.add(inquiry);
    }

    @Override
    public void deleteInquiry(String id, long inquiryId) {
        List<Inquiry> inquiries = repository.get(id);
        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId() == inquiryId) {
                inquiries.remove(i);
                break;
            }
        }

    }

    @Override
    public void updateInquiry(String id, Inquiry inquiry) {
        List<Inquiry> inquiries = repository.get(id);
        for(int i = 0; i < inquiries.size(); i++) {
            if(inquiries.get(i).getInquiryId() == inquiry.getInquiryId()) {
                inquiries.set(i, inquiry);
                break;
            }
        }
    }
}
