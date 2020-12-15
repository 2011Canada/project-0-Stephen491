package com.revature.debuggers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.util.BankDatabaseConnection;

public class DatabaseConnectionDebugger {
	public static void main(String args[]) {
		Connection newConn;
		try {
			newConn = BankDatabaseConnection.getConnection();
			Statement st = newConn.createStatement(); 
			String query = "SELECT * FROM UserAccounts WHERE firstname= \'John\'";
			
			System.out.println(st.execute(query));
			
			ResultSet results = st.getResultSet();
			results.next();
			System.out.println(results.getString("lastname"));
			
			query = "INSERT INTO UserAccounts VALUES (\'113\',\'Jane\', \'Doe\',\'wordword\', 5, true);";
			System.out.println(st.execute(query));
			
			
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
		
		
		
	}
}
