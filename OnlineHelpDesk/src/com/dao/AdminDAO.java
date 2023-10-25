package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbUtil.DatabaseManager;
import com.model.Admin;

public class AdminDAO {
	
	public Admin getAdminByUsername(String username) {
		
		Admin admin = null;
		Connection conn = DatabaseManager.getConnection();
		
		try {
			String query = "SELECT * FROM agent WHERE username=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				int dbId = result.getInt("admin_id");
				String dbUsername = result.getString("username");
				String dbPassword = result.getString("password");
				String dbEmail = result.getString("email");
				
				admin = new Admin(dbId, dbUsername, dbPassword, dbEmail);
				result.close();
                stmt.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return admin;
	}
}
