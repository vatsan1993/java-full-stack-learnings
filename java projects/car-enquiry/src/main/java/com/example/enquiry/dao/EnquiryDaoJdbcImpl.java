package com.example.enquiry.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.enquiry.entity.Enquiry;
import com.example.enquiry.entity.Status;
import com.example.enquiry.entity.VehicleType;
import com.example.enquiry.exceptions.EnquiryException;

public class EnquiryDaoJdbcImpl implements EnquiryDao {

    private static final String INSERT_ENQUIRY =
    "INSERT INTO enquiries (enquiry_id, customer_name, mobile, vehicle_type, budget_from, budget_to, enquiry_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ENQUIRY_STATUS_BY_ID =
    "UPDATE enquiries SET enquiry_status = ? WHERE enquiry_id = ?";
    private static final String SELECT_ALL_ENQUIRIES =
    "SELECT * FROM enquiries";
    private static final String SELECT_CLOSED_ENQUIRIES =
    "SELECT * FROM enquiries WHERE enquiry_status = 'CLOSED'";
    private static final String SELECT_ENQUIRY_BY_MOBILE =
    "SELECT * FROM enquiries WHERE mobile = ?";
    private static final String DELETE_ENQUIRY_BY_ID =
    "DELETE FROM enquiries WHERE enquiry_id = ?";

    @Override
    public boolean addEnquiry(Enquiry enquiry) throws EnquiryException {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EnquiryException("Unable to connect to database");
        }

        try (PreparedStatement stmt = conn.prepareStatement(INSERT_ENQUIRY);) {
            stmt.setInt(1, enquiry.getId());
            stmt.setString(2, enquiry.getCustomerName());
            stmt.setString(3, enquiry.getMobile().toString());
            stmt.setString(4, enquiry.getVehicleType().toString());
            stmt.setString(5, enquiry.getBudgetFrom().toString());
            stmt.setString(6, enquiry.getBudgetTo().toString());
            stmt.setString(7, enquiry.getStatus().toString());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new EnquiryException("Unable to add enquiry");
        }
        return true;
    }

    @Override
    public boolean updateEnquiryStatusById(Integer enquiryId, Status status) throws EnquiryException {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new EnquiryException("Unable to connect to database");
        }
        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_ENQUIRY_STATUS_BY_ID);) {
            stmt.setString(1, status.toString());
            stmt.setInt(2, enquiryId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new EnquiryException("Unable to add update status");
        }
        return true;
    }

    @Override
    public List<Enquiry> getAllEnquiries() throws EnquiryException {
        List<Enquiry> enquiries = new ArrayList<>();
        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new EnquiryException("Unable to connect to database");
        }

        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ENQUIRIES);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enquiry enquiry = new Enquiry();
                enquiry.setId(rs.getInt("enquiry_id"));
                enquiry.setCustomerName(rs.getString("customer_name"));
                enquiry.setMobile(new BigInteger(rs.getString("mobile")));
                String vehicleType = rs.getString("vehicle_type");
                VehicleType type = VehicleType.valueOf(vehicleType);
                enquiry.setVehicleType(type);
                enquiry.setBudgetFrom(new BigInteger(rs.getString("budget_from")));
                enquiry.setBudgetTo(new BigInteger(rs.getString("budget_to")));
                String status = rs.getString("enquiry_status");
                Status enquiryStatus = Status.valueOf(status);
                enquiry.setStatus(enquiryStatus);
                enquiries.add(enquiry);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new EnquiryException("Unable get enquiries");
        }

        if(enquiries.isEmpty()) {
            enquiries = null;
        }
        return enquiries;
    }

    @Override
    public List<Enquiry> getClosedEnquiries() throws EnquiryException {
        // TODO Auto-generated method stub
        List<Enquiry> enquiries = new ArrayList<>();
        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new EnquiryException("Unable to connect to database");
        }

        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_CLOSED_ENQUIRIES);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enquiry enquiry = new Enquiry();
                enquiry.setId(rs.getInt("enquiry_id"));
                enquiry.setCustomerName(rs.getString("customer_name"));
                enquiry.setMobile(new BigInteger(rs.getString("mobile")));
                String vehicleType = rs.getString("vehicle_type");
                VehicleType type = VehicleType.valueOf(vehicleType);
                enquiry.setVehicleType(type);
                enquiry.setBudgetFrom(new BigInteger(rs.getString("budget_from")));
                enquiry.setBudgetTo(new BigInteger(rs.getString("budget_to")));
                String status = rs.getString("enquiry_status");
                Status enquiryStatus = Status.valueOf(status);
                enquiry.setStatus(enquiryStatus);
                enquiries.add(enquiry);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            throw new EnquiryException("Unable to get the values");
        }

        if(enquiries.isEmpty()) {
            enquiries = null;
        }
        return enquiries;
    }

    @Override
    public Enquiry getEnquiryByMobile(BigInteger mobile) throws EnquiryException {
        // TODO Auto-generated method stub

        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new EnquiryException("Unable to connect to database");
        }
        Enquiry enquiry = null;

        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ENQUIRY_BY_MOBILE);) {
            stmt.setString(1, mobile.toString());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                enquiry = new Enquiry();
                enquiry.setId(rs.getInt("enquiry_id"));
                enquiry.setCustomerName(rs.getString("customer_name"));
                enquiry.setMobile(new BigInteger(rs.getString("mobile")));
                String vehicleType = rs.getString("vehicle_type");
                VehicleType type = VehicleType.valueOf(vehicleType);
                enquiry.setVehicleType(type);
                enquiry.setBudgetFrom(new BigInteger(rs.getString("budget_from")));
                enquiry.setBudgetTo(new BigInteger(rs.getString("budget_to")));
                String status = rs.getString("enquiry_status");
                Status enquiryStatus = Status.valueOf(status);
                enquiry.setStatus(enquiryStatus);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new EnquiryException("Unable to get the values");
        }


        return enquiry;
    }

    @Override
    public boolean deleteEnquiryById(Integer enquiryId) throws EnquiryException {
        // TODO Auto-generated method stub
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            // conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new EnquiryException("Unable to connect to database");
        }

        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_ENQUIRY_BY_ID);) {
            stmt.setInt(1, enquiryId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new EnquiryException("Unable to delete the enquiry");
        }

        return true;
    }

}
