package com.servlet;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketId = Integer.parseInt(request.getParameter("ticketId")); // Change this to how you pass the ticket ID

        // Fetch ticket details using the ticket ID
        TicketDAO ticketdao = new TicketDAO();
        Ticket ticket = ticketdao.getTicketDetails(ticketId);

        if (ticket != null) {
            // Fetch associated reference image URLs
            RefImageDAO refimagedao = new RefImageDAO();
            List<RefImage> refImages = refimagedao.getRefImagesByTicketId(ticketId);
            
            // Fetch comments 
            CommentDAO commentdao = new CommentDAO();
            List<Comment> comments = commentdao.getCommentsByTicketId(ticketId);

            // Set ticket and image data in request attributes
            request.setAttribute("ticket", ticket);
            request.setAttribute("refImages", refImages);

            // Forward to a JSP page for rendering the ticket details
            RequestDispatcher dispatcher = request.getRequestDispatcher("ticketDetails.jsp"); // Change this to the JSP you want to use
            dispatcher.forward(request, response);
        } else {
            // Handle the case when the ticket is not found
            response.sendRedirect("errorPage.jsp"); // Change this to an appropriate error page
        }
	}

}
