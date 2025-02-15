package com.example.enquiry.dao;

import java.math.BigInteger;
import java.util.List;

import com.example.enquiry.entity.Enquiry;
import com.example.enquiry.entity.Status;
import com.example.enquiry.exceptions.EnquiryException;

public interface  EnquiryDao {
    public boolean addEnquiry(Enquiry enquiry) throws EnquiryException ;
    public boolean updateEnquiryStatusById(Integer enquiryId, Status status) throws EnquiryException ;
    public List<Enquiry> getAllEnquiries() throws EnquiryException ;
    public List<Enquiry> getClosedEnquiries() throws EnquiryException ;
    public Enquiry getEnquiryByMobile(BigInteger mobile) throws EnquiryException ;
    public boolean deleteEnquiryById(Integer enquiryId) throws EnquiryException ;
}
