package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.dbUtil.DatabaseManager;
import com.model.User;

public class UserDAO {
	
	public User getUserByUsername(String username) {
		User user = null;
		Connection conn = DatabaseManager.getConnection();
		
		try {
			
			String query = "SELECT * FROM user WHERE username=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				int dbId = result.getInt("user_id");
                String dbUsername = result.getString("username");
                String dbFirstName = result.getString("first_name");
                String dbLastName = result.getString("last_name");
                String dbPassword = result.getString("password");
                String dbEmail = result.getString("email");
                String dbPhone = result.getString("phone");
                Date dbDateOfBirth = result.getDate("date_of_birth");
                String dbGender = result.getString("gender");
                String dbProfileImgUrl = result.getString("profile_img_url");
                
                user = new User(dbId, dbUsername, dbFirstName, dbLastName, dbPassword, dbEmail, dbPhone, dbDateOfBirth, dbGender, dbProfileImgUrl);
                
                result.close();
                stmt.close();
            }	
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return user;
	}
	
	public void addUser(User user) {
		
		Connection conn = DatabaseManager.getConnection();
		
		try {
			String query = "INSERT INTO User (username, first_name, last_name, password, email, phone, date_of_birth, gender, profile_img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getFirst_name());
			stmt.setString(3, user.getLast_name());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getPhone());
			stmt.setDate(7, new java.sql.Date(user.getDate_of_birth().getTime()));
			stmt.setString(8, user.getGender());
			stmt.setString(9, user.getProfile_img());
			
			stmt.executeUpdate();
			
			stmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DatabaseManager.closeConnection(conn);
		}
	}
}
