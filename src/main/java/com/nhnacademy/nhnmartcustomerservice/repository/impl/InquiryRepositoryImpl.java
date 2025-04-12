package com.nhnacademy.nhnmartcustomerservice.repository.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.Inquiry;
import com.nhnacademy.nhnmartcustomerservice.repository.InquiryRepository;
import org.springframework.stereotype.Component;

import java.util.*;

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

        List<Inquiry> list = repository.get(id);
        list.sort(Comparator.comparing(Inquiry::getCreatedTime).reversed());

        return list;
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
    public Inquiry getInquiryByInquiryId(long inquiryId) {

        for(String key : repository.keySet()) {
            List<Inquiry> list = repository.get(key);

            for(int i = 0; i < list.size(); i++) {
                long id = list.get(i).getInquiryId();

                if(id == inquiryId) {
                    return list.get(i);
                }

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
        inquiries.addFirst(inquiry);
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

    @Override
    public List<Inquiry> getNotAnsweredInquiries() {
        List<Inquiry> inquiries = new ArrayList<>();

        for(String key : repository.keySet()) {
            List<Inquiry> getInquiries = repository.get(key);

            for(int i = 0; i < getInquiries.size(); i++) {
                if(!getInquiries.get(i).isAnswered()) {
                    inquiries.add(getInquiries.get(i));
                }
            }
        }
        inquiries.sort(Comparator.comparing(Inquiry::getCreatedTime).reversed());

        return inquiries;
    }
}
