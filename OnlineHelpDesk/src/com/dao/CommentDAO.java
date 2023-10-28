package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbUtil.DatabaseManager;
import com.model.Comment;

public class CommentDAO {

	public void addComment(Comment comment) {
        Connection conn = DatabaseManager.getConnection();

        try {
            String query = "INSERT INTO Comment (ticket_id, user_id, comment_text) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, comment.getTicketId());
            stmt.setInt(2, comment.getUser_id()); 
            stmt.setInt(2, comment.getAgent_id()); 
            stmt.setString(3, comment.getCommentText());

            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }

    }
	
	
	public List<Comment> getCommentsByTicketId(int ticketId) {
        Connection conn = DatabaseManager.getConnection();
        List<Comment> comments = new ArrayList<>();

        try {
            String query = "SELECT * FROM comments WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ticketId);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int commentId = result.getInt("comment_id");
                int userId = result.getInt("user_id");
                int agentId = result.getInt("agent_id");
                String commentText = result.getString("comment_text");
                // Assuming your database table has a timestamp column
                Date timestamp = result.getDate("timestamp");

                Comment comment = new Comment(commentId, ticketId, userId, agentId, commentText, timestamp);
                comments.add(comment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return comments;
    }
	
	
	public boolean deleteComment(int commentId) {
        Connection conn = DatabaseManager.getConnection();

        try {
            String query = "DELETE FROM Comment WHERE comment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, commentId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions, log, or throw as needed
        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return false;
    }
}
