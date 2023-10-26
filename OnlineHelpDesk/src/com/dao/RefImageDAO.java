package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dbUtil.DatabaseManager;
import com.model.RefImage;

public class RefImageDAO {
	
	public void addRefImage(RefImage image) {
		Connection conn = DatabaseManager.getConnection();
		
		try {
			String query = "INSERT INTO ReferenceImage (ticket_id, image_path) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, image.getTicketId());
            stmt.setString(2, image.getPath());
            
            stmt.executeUpdate();
            stmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			
			
		}
	}
}
