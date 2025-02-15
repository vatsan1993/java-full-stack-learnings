package com.example.enquiry.exceptions;

import java.sql.SQLException;

public class EnquiryException extends SQLException {
    public EnquiryException(String message) {
        super(message);
    }

}
