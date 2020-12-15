package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankDatabaseConnection {

	
	public static Connection getConnection() throws SQLException {
		
		
		Properties prop = new Properties();
		String fileName = "src/com/revature/util/db.config";
		InputStream is = null;
		
		try {
		    is = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
		   e.printStackTrace();
		}
		try {
		    prop.load(is);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		Connection conn = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", prop.getProperty("dbuser"));
	    connectionProps.put("password", prop.getProperty("dbpassword"));
		
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", connectionProps);
	    
	    //System.out.println("Connected to database");
	    
	    return conn;
		
		
		
		
	}
}
