package com.example.enquiry.entity;

import java.math.BigInteger;

public class Enquiry {
    private Integer id;
    private String customerName;
    private BigInteger mobile;
    private VehicleType vehicleType;
    private BigInteger budgetFrom, budgetTo;
    private Status status;

    public Enquiry() {
    }

    public Enquiry(Integer id, String customerName, BigInteger mobile, VehicleType vehicleType, BigInteger budgetFrom, BigInteger budgetTo, Status status) {
        this.id = id;
        this.customerName = customerName;
        this.mobile = mobile;
        this.vehicleType = vehicleType;
        this.budgetFrom = budgetFrom;
        this.budgetTo = budgetTo;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigInteger getMobile() {
        return mobile;
    }

    public void setMobile(BigInteger mobile) {
        this.mobile = mobile;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigInteger getBudgetFrom() {
        return budgetFrom;
    }

    public void setBudgetFrom(BigInteger budgetFrom) {
        this.budgetFrom = budgetFrom;
    }

    public BigInteger getBudgetTo() {
        return budgetTo;
    }

    public void setBudgetTo(BigInteger budgetTo) {
        this.budgetTo = budgetTo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enquiry [id=" + id + ", customerName=" + customerName + ", mobile=" + mobile + ", vehicleType="
                + vehicleType + ", budgetFrom=" + budgetFrom + ", budgetTo=" + budgetTo + ", status=" + status + "]";
    }


}
