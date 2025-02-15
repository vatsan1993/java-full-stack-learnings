package com.example.ims.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory  {
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/imsDb","root", "ssvnka302#");
            return conn;
        } catch (SQLException e) {
            throw new SQLException("DB Connection failed");
        }

    }
}
