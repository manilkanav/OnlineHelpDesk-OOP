package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dbUtil.DatabaseManager;
import com.model.Ticket;

public class AgentTicketAssignmentDAO {
	public List<Ticket> getAssignedTickets(int agentId) {
		List<Ticket> tickets = new ArrayList<>();
		
		Connection conn = DatabaseManager.getConnection();
		TicketDAO ticketdao = new TicketDAO();
		
		try {
			String query = "SELECT ticket_id FROM AgentTicketAssignment WHERE agent_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, agentId);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				int ticketId = result.getInt("ticket_id");
				Ticket ticket = ticketdao.getTicketDetails(ticketId);
				tickets.add(ticket);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return tickets;
	}
	
	public void deleteAssignmentsForTicket(int ticketId) {
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "DELETE FROM AgentTicketAssignment WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ticketId);

            // Execute the delete query
            stmt.executeUpdate();

            // Close the statement
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }
    }
	
	public boolean assignTicketToAgent(int agentId, int ticketId) {
        Connection conn = DatabaseManager.getConnection();
        boolean success = false;
        try {
            String query = "INSERT INTO AgentTicketAssignment (agent_id, ticket_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, agentId);
            stmt.setInt(2, ticketId);

            // Execute the insert query to assign the ticket to the agent
            stmt.executeUpdate();
            
            int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            success = true;
	        }

            // Close the statement
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }
        
        return success;
    }
}
