package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbUtil.DatabaseManager;
import com.model.Ticket;

public class TicketDAO {

	public int createTicket(Ticket ticket) {
		Connection conn = DatabaseManager.getConnection();
		int generatedTicketId = -1;
		
		try {
			String query = "INSERT INTO ticket (user_id, subject, description, status, type) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setLong(1, ticket.getUserId());
            stmt.setString(2, ticket.getSubject());
            stmt.setString(3, ticket.getDescription());
            stmt.setString(4, ticket.getStatus());
            stmt.setString(5, ticket.getType());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 1) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedTicketId = generatedKeys.getInt(1);
                }
            }
            
            stmt.close();

		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return generatedTicketId;
	}
	
	public List<Ticket> getTicketsByUserId(int userId) {
		Connection conn = DatabaseManager.getConnection();
		List<Ticket> tickets = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM ticket WHERE user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            
            ResultSet result = stmt.executeQuery();
           
            while (result.next()) {
            	int ticketId = result.getInt("ticket_id");
                int retrievedUserId = result.getInt("user_id");
                String subject = result.getString("subject");
                String description = result.getString("description");
                String status = result.getString("status");
                String type = result.getString("type");
                Date createdAt = result.getDate("created_at");
                Date resolvedAt = result.getDate("resolved_at");
                
                Ticket ticket = new Ticket(ticketId, retrievedUserId, subject, description, status, type, createdAt, resolvedAt);
                tickets.add(ticket);
            	
            }
            
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeConnection(conn);
		}
		
		return tickets;
	}
	
	public Ticket getTicketDetails(int ticketId) {
        Connection conn = DatabaseManager.getConnection();
        Ticket ticket = null;

        try {
            String query = "SELECT * FROM ticket WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ticketId);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int retrievedUserId = result.getInt("user_id");
                String subject = result.getString("subject");
                String description = result.getString("description");
                String status = result.getString("status");
                String type = result.getString("type");
                Date createdAt = result.getDate("created_at");
                Date resolvedAt = result.getDate("resolved_at");

                ticket = new Ticket(ticketId, retrievedUserId, subject, description, status, type, createdAt, resolvedAt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return ticket;
    }
	
	
	 public List<Ticket> getOpenTickets() {
	        Connection conn = DatabaseManager.getConnection();
	        List<Ticket> openTickets = new ArrayList<>();

	        try {
	            String query = "SELECT * FROM ticket WHERE status = 'Open'";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            ResultSet result = stmt.executeQuery();

	            while (result.next()) {
	                int ticketId = result.getInt("ticket_id");
	                int userId = result.getInt("user_id");
	                String subject = result.getString("subject");
	                String description = result.getString("description");
	                String status = result.getString("status");
	                String type = result.getString("type");
	                Date createdAt = result.getDate("created_at");
	                Date resolvedAt = result.getDate("resolved_at");
	                
	                System.out.println("ticket dao" + ticketId);

	                Ticket ticket = new Ticket(ticketId, userId, subject, description, status, type, createdAt, resolvedAt);
	                System.out.println("ticket dao" + ticket);
	                openTickets.add(ticket);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseManager.closeConnection(conn);
	        }

	        return openTickets;
	    }
	 
	 
	 public List<Ticket> getEscalatedTickets() {
	        Connection conn = DatabaseManager.getConnection();
	        List<Ticket> escalatedTickets = new ArrayList<>();

	        try {
	            String query = "SELECT * FROM ticket WHERE status = 'Escalated'";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            ResultSet result = stmt.executeQuery();

	            while (result.next()) {
	                int ticketId = result.getInt("ticket_id");
	                int userId = result.getInt("user_id");
	                String subject = result.getString("subject");
	                String description = result.getString("description");
	                String status = result.getString("status");
	                String type = result.getString("type");
	                Date createdAt = result.getDate("created_at");
	                Date resolvedAt = result.getDate("resolved_at");

	                Ticket ticket = new Ticket(ticketId, userId, subject, description, status, type, createdAt, resolvedAt);
	                escalatedTickets.add(ticket);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseManager.closeConnection(conn);
	        }

	        return escalatedTickets;
	    }
	 
	 public boolean setStatusInProgress(int ticketId) {
		    Connection conn = DatabaseManager.getConnection();
		    boolean success = false;

		    try {
		        String query = "UPDATE ticket SET status = 'In Progress' WHERE ticket_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setInt(1, ticketId);

		        int rowsAffected = stmt.executeUpdate();

		        if (rowsAffected > 0) {
		            success = true;
		        }

		        stmt.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DatabaseManager.closeConnection(conn);
		    }

		    return success;
		}
	 
	 
	 public boolean setStatusEscalated(int ticketId) {
		 Connection conn = DatabaseManager.getConnection();
		 boolean success = false;

		    try {
		        String query = "UPDATE ticket SET status = 'Escalated' WHERE ticket_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setInt(1, ticketId);

		        int rowsAffected = stmt.executeUpdate();

		        if (rowsAffected > 0) {
		            success = true;
		        }

		        stmt.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DatabaseManager.closeConnection(conn);
		    }

		    return success;
	 }
	 
	 public boolean setStatusResolve(int ticketId) {
		    Connection conn = DatabaseManager.getConnection();
		    
		    try {
		        String query = "UPDATE ticket SET status = 'Resolved', resolved_at = NOW() WHERE ticket_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setInt(1, ticketId);
		        
		        int rowsAffected = stmt.executeUpdate();
		        
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DatabaseManager.closeConnection(conn);
		    }
		    
		    return false;
		}
	 
	 public boolean deleteTicket(int ticketId) {
		    Connection conn = DatabaseManager.getConnection();
		    boolean success = false;

		    try {
		        String query = "DELETE FROM ticket WHERE ticket_id = ?";
		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setInt(1, ticketId);

		        int rowsAffected = stmt.executeUpdate();

		        if (rowsAffected > 0) {
		            success = true;
		        }

		        stmt.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DatabaseManager.closeConnection(conn);
		    }

		    return success;
		}
	
}
