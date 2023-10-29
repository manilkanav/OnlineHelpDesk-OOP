package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommentDAO;
import com.model.Comment;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/addcomment")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the parameters from the request
		int userId = 0;
		int ticketId = 0;
		
		try {
			ticketId = Integer.parseInt(request.getParameter("ticketId"));
			System.out.println(ticketId);
	        userId = Integer.parseInt(request.getParameter("UserId"));
		} catch(Exception e) {
			e.printStackTrace();
		}
        
        String commentText = request.getParameter("commentText");

        // Create a Comment object
        Comment comment = new Comment(ticketId, userId, commentText);
        System.out.println(comment.getTicketId());

        // Add the comment to the database
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.addComment(comment);

        // Redirect to the ticket details page
        response.sendRedirect("ticketdetails?ticketId=" + ticketId);
	}

}
