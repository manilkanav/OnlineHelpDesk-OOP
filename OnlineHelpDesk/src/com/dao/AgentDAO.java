package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.dbUtil.DatabaseManager;
import com.model.Agent;

public class AgentDAO {
	
	public Agent getAgentByUsername(String username) {
		Agent agent = null;
		Connection conn = DatabaseManager.getConnection();
		
		try {
			String query = "SELECT * FROM agent WHERE username=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				int dbId = result.getInt("agent_id");
				String dbUsername = result.getString("username");
				String dbpassowrd = result.getString("password");
				String dbEmail = result.getString("email");
				String dbFirstName = result.getString("first_name");
				String dbLastName = result.getString("last_name");
				Date dbDateOfBirth = result.getDate("date_of_birth");
				String dbGender = result.getString("gender");
				String dbAccountStatus = result.getString("account_status");
				String dbDepartment = result.getString("department");
				String dbType = result.getString("type");
				
				agent = new Agent(dbId, dbUsername, dbpassowrd, dbEmail, dbFirstName, dbLastName, dbDateOfBirth, dbGender, dbAccountStatus, dbDepartment, dbType);
				
				result.close();
	            stmt.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return agent;
	}
}
