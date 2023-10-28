package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TicketDAO;

/**
 * Servlet implementation class ResolveTicketServlet
 */
@WebServlet("/resolve")
public class ResolveTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Assuming that the ticket ID is passed as a parameter in the request
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));

        // Check if the user has admin privileges or the ticket is assigned to them
        HttpSession session = request.getSession();
        String accountType = (String) session.getAttribute("accountType");
        String assignedAgent = (String) session.getAttribute("assignedAgent"); // Assuming you have this attribute to check ticket assignment

        if (accountType != null && (accountType.equals("admin") || assignedAgent != null)) {
            TicketDAO ticketDAO = new TicketDAO();
            boolean success = ticketDAO.setStatusResolve(ticketId);

            if (success) {
                // Ticket resolved successfully
                response.sendRedirect("ticketDetails.jsp?ticketId=" + ticketId);
            }
            
        } else {
           
            response.sendRedirect("userLogin.jsp"); // Redirect to an access denied page
        }
	}

}
