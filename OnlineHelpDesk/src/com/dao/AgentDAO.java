package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public List<Agent> getAllAgents() {
        List<Agent> agents = new ArrayList<>();
        Connection conn = DatabaseManager.getConnection();

        try {
            String query = "SELECT * FROM agent";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int dbId = result.getInt("agent_id");
                String dbUsername = result.getString("username");
                String dbPassword = result.getString("password");
                String dbEmail = result.getString("email");
                String dbFirstName = result.getString("first_name");
                String dbLastName = result.getString("last_name");
                Date dbDateOfBirth = result.getDate("date_of_birth");
                String dbGender = result.getString("gender");
                String dbAccountStatus = result.getString("account_status");
                String dbDepartment = result.getString("department");
                String dbType = result.getString("type");

                Agent agent = new Agent(dbId, dbUsername, dbPassword, dbEmail, dbFirstName, dbLastName, dbDateOfBirth, dbGender, dbAccountStatus, dbDepartment, dbType);
                agents.add(agent);
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return agents;
    }
	
	public int createAgent(Agent agent) {
        Connection conn = DatabaseManager.getConnection();
        int generatedAgentId = -1;

        try {
            String query = "INSERT INTO Agent (username, password, email, first_name, last_name, gender, date_of_birth, account_status, department, type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, agent.getUsername());
            stmt.setString(2, agent.getPassword());
            stmt.setString(3, agent.getEmail());
            stmt.setString(4, agent.getFirst_name());
            stmt.setString(5, agent.getLast_name());
            stmt.setString(6, agent.getGender());
            stmt.setDate(7, new java.sql.Date (agent.getDate_of_birth().getTime())); // Assuming date_of_birth is a SQL Date
            stmt.setString(8, agent.getAccount_status());
            stmt.setString(9, agent.getDepartment());
            stmt.setString(10, agent.getType());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedAgentId = generatedKeys.getInt(1);
                }
            }

            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return generatedAgentId;
    }
	
	public boolean deleteAgentByUsername(String username) {
	    Connection conn = DatabaseManager.getConnection();

	    try {
	        String query = "DELETE FROM Agent WHERE username = ?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, username);

	        int rowsAffected = stmt.executeUpdate();
	        stmt.close();

	        // Check if the delete operation was successful
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DatabaseManager.closeConnection(conn);
	    }

	    return false; // Return false in case of any errors
	}
	
}
