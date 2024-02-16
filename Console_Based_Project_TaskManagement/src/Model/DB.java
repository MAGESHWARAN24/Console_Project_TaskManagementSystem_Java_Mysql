package Model;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import View.Dashboard;

public class DB {
	static Connection getConnection() {
	    String jdbcUrl = "jdbc:mysql://localhost:3306/taskmanagementsystemdb";
	    String jdbcUsername = "root";
	    String jdbcPassword = "Mypassword1234";
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
	    } catch (ClassNotFoundException e) {
	        System.err.println("MySQL JDBC driver not found.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("Failed to connect to the database.");
	        e.printStackTrace();
	    }
	    return null;
	}

	public static boolean signUp(Dashboard d) {		
		String query = "INSERT INTO users(emailid,password,role) VALUES (?,?,?);";
		try {		
			PreparedStatement pstm = getConnection().prepareStatement(query);
			pstm.setString(1, d.getUserID());
			pstm.setString(2, d.getPassword());		
			pstm.setString(3, d.getRole());			
			int res = pstm.executeUpdate();
			if(res > 0) {
				System.out.println("<------------ SignUp Successfully Completed ------------>");
				return true;
			}
		}
		catch ( SQLException e) {
			System.out.println(e.toString());
		}
		return false;
	}
	public static ArrayList<String> signIn(Dashboard d) {
		String query = "SELECT role,id,password FROM users WHERE emailid = ?";
		ArrayList<String> r = new ArrayList<>();
    	try {		
			PreparedStatement pstm = getConnection().prepareStatement(query);
			pstm.setString(1, d.getUserID());
			ResultSet res = pstm.executeQuery();
			if(res.next()) {
				if(d.getPassword().equals(res.getString(3))) {
					r.add(res.getString(1));
					r.add(""+res.getInt(2));
				}
				return r;	
			}
		}
		catch ( SQLException e) {
			System.out.println(e.toString());
		}
    	return r;
	}
}
