package com.example.ims.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory  {
    private static DataSource datasource;
    
    public static Connection getConnection() throws SQLException, NamingException {
    	if(datasource == null) {
    		InitialContext context = new InitialContext();
    		// The parameter for the lookup will be provided by the server admin.
    		datasource = (DataSource) context.lookup("java:/comp/env/jdbc/MyDB");
    	}
    	
    	return datasource.getConnection();
    }
}
