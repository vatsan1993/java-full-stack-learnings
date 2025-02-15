package com.example.enquiry.services;

import java.math.BigInteger;
import java.util.List;

import com.example.enquiry.entity.Enquiry;
import com.example.enquiry.exceptions.EnquiryException;

public interface EnquiryService {
    public boolean addEnquiry(Enquiry enquiry) throws EnquiryException ;
    public boolean updateEnquiryStatusById(Integer enquiryId, String status) throws EnquiryException ;
    public List<Enquiry> getAllEnquiries() throws EnquiryException ;
    public List<Enquiry> getClosedEnquiries() throws EnquiryException ;
    public Enquiry getEnquiryByMobile(BigInteger mobile) throws EnquiryException ;
    public boolean deleteEnquiryById(Integer enquiryId) throws EnquiryException ;

}
