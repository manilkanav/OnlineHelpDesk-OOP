package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommentDAO;
import com.dao.RefImageDAO;
import com.dao.TicketDAO;
import com.model.Comment;
import com.model.RefImage;
import com.model.Ticket;

/**
 * Servlet implementation class TicketDetailsServlet
 */
@WebServlet("/ticketdetails")
public class TicketDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_DIRECTORY = "Uploads" + File.separator + "TicketRefImg";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getQueryString();
		
		int ticketId = 0;
        if (query != null) {
            String[] pathParts = query.split("=");
            if (pathParts.length == 2) {
                ticketId = Integer.parseInt(pathParts[1]);
            }
        }
        System.out.println(query);
        System.out.println(ticketId);
 // Change this to how you pass the ticket ID

        // Fetch ticket details using the ticket ID
        TicketDAO ticketdao = new TicketDAO();
        Ticket ticket = ticketdao.getTicketDetails(ticketId);

        if (ticket != null) {
            // Fetch associated reference image URLs
            RefImageDAO refimagedao = new RefImageDAO();
            List<RefImage> refImages = refimagedao.getRefImagesByTicketId(ticketId);
            
            for(RefImage image : refImages) {
            	String imgLocation = IMAGE_DIRECTORY + File.separator + image.getPath();
            	image.setPath(imgLocation);
            }
            
            // Fetch comments 
            CommentDAO commentdao = new CommentDAO();
            List<Comment> comments = commentdao.getCommentsByTicketId(ticketId);

            // Set ticket and image data in request attributes
            request.setAttribute("ticket", ticket);
            request.setAttribute("refImages", refImages);
            request.setAttribute("comments", comments);

            // Forward to a JSP page for rendering the ticket details
            RequestDispatcher dispatcher = request.getRequestDispatcher("ticketDetails.jsp"); 
            dispatcher.forward(request, response);
        } else {
            // Handle the case when the ticket is not found
            response.sendRedirect("errorPage.jsp"); // Change this to an appropriate error page
        }
	}

}
