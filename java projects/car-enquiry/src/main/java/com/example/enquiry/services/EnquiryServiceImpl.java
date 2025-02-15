package com.example.enquiry.services;

import java.math.BigInteger;
import java.util.List;

import com.example.enquiry.dao.EnquiryDao;
import com.example.enquiry.entity.Enquiry;
import com.example.enquiry.entity.Status;
import com.example.enquiry.entity.VehicleType;
import com.example.enquiry.exceptions.EnquiryException;

public class EnquiryServiceImpl implements EnquiryService {
    EnquiryDao enquiryDao;

    public EnquiryServiceImpl(EnquiryDao enquiryDao) {
        this.enquiryDao = enquiryDao;
    }

    private boolean isValidId( Integer id) {
        return id != null && id > 0;
    }

    private boolean isValidStatus(String status) {
        return status != null &&
        !status.isEmpty() &&
        status.equals("OPEN") ||
        status.equals("CLOSED");
    }

    private boolean isMobileValid(BigInteger mobile) {
        return mobile != null && mobile.toString().length() == 10;
    }

    private boolean isCustomerNameValid(String customerName) {
        return customerName != null && !customerName.isEmpty() && customerName.length() >= 3 && customerName.length() <= 50;
    }

    private boolean isVehicleTypeValid(String vehicleType) {

        return vehicleType != null && !vehicleType.isEmpty() && VehicleType.valueOf(vehicleType) != null;
    }

    private boolean isBudgetValid(BigInteger budget) {
        return budget != null && budget.compareTo(budget.ZERO) > 1;
    }

    private boolean isBudgeValid(BigInteger budgetFrom, BigInteger budgetTo) {
        return isBudgetValid(budgetTo) &&
        isBudgetValid(budgetFrom) &&
        budgetFrom .compareTo(budgetTo) == 0;
    }

    private boolean isEnquiryStatusValid(String enquiryStatus) {
        return enquiryStatus != null &&
        !enquiryStatus.isEmpty() &&
        Status.valueOf(enquiryStatus) != null;
    }

    private boolean isEnquiryValid(Enquiry enquiry) throws EnquiryException {
        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        if(!isValidId(enquiry.getId())){
            throw new EnquiryException("Invalid enquiry id");
        }
        if (!isCustomerNameValid(enquiry.getCustomerName())) {
            throw new EnquiryException("Invalid customer name");
        }
        if(!isMobileValid(enquiry.getMobile())) {
            throw new EnquiryException("Invalid mobile number");
        }
        if(!isVehicleTypeValid(enquiry.getVehicleType().toString())) {
            throw new EnquiryException("Invalid vehicle type");
        }
        if(!isBudgeValid(enquiry.getBudgetFrom(), enquiry.getBudgetTo())) {
            throw new EnquiryException("Invalid budget");
        }
        if(!isEnquiryStatusValid(enquiry.getStatus().toString())) {
            throw new EnquiryException("Invalid status");
        }
        return true;
    }

    @Override
    public boolean addEnquiry(Enquiry enquiry) throws EnquiryException {
        if(isEnquiryValid(enquiry)) {
            return enquiryDao.addEnquiry(enquiry);
        }
        throw new EnquiryException("Invalid enquiry details");
    }

    @Override
    public boolean updateEnquiryStatusById(Integer enquiryId, String status) throws EnquiryException {
        if(isValidId(enquiryId) && isValidStatus(status)) {
            Status enquiryStatus = Status.valueOf(status);
            return enquiryDao.updateEnquiryStatusById(enquiryId, enquiryStatus);
        }
        throw new EnquiryException("Invalid enquiryId or status");
    }

    @Override
    public List<Enquiry> getAllEnquiries() throws EnquiryException {
        // TODO Auto-generated method stub
        return enquiryDao.getAllEnquiries();
    }

    @Override
    public List<Enquiry> getClosedEnquiries() throws EnquiryException {
        // TODO Auto-generated method stub
        return enquiryDao.getClosedEnquiries();
    }

    @Override
    public Enquiry getEnquiryByMobile(BigInteger mobile) throws EnquiryException {
        // TODO Auto-generated method stub
        if(isMobileValid(mobile)) {
            return enquiryDao.getEnquiryByMobile(mobile);
        }
        throw new EnquiryException("Invalid mobile number");
    }

    @Override
    public boolean deleteEnquiryById(Integer enquiryId) throws EnquiryException {
        // TODO Auto-generated method stub
        if(isValidId(enquiryId)) {
            return enquiryDao.deleteEnquiryById(enquiryId);
        }
        throw new EnquiryException("Invalid enquiryId");
    }
}
