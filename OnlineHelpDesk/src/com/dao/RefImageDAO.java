package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<RefImage> getRefImagesByTicketId(int ticketId) {
        Connection conn = DatabaseManager.getConnection();
        List<RefImage> refImages = new ArrayList<>();

        try {
            String query = "SELECT * FROM ReferenceImage WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ticketId);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int imageId = result.getInt("image_id");
                String path = result.getString("image_path");

                RefImage refImage = new RefImage(imageId, ticketId, path);
                refImages.add(refImage);
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection(conn);
        }

        return refImages;
    }
}
