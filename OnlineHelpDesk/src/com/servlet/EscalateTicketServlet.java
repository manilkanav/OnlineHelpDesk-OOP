package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AgentTicketAssignmentDAO;
import com.dao.TicketDAO;

/**
 * Servlet implementation class EscalateTicketServlet
 */
@WebServlet("/escalate")
public class EscalateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));

        // Check if there are assignments for this ticket and delete them
        AgentTicketAssignmentDAO assignmentDAO = new AgentTicketAssignmentDAO();
        assignmentDAO.deleteAssignmentsForTicket(ticketId);

        // Escalate the ticket status
        TicketDAO ticketDAO = new TicketDAO();
        boolean success = ticketDAO.setStatusEscalated(ticketId);

        if (success) {
            // Set a success message
            request.setAttribute("successMessage", "Ticket escalated successfully.");

            // Use RequestDispatcher to include the success message in the forward
            response.sendRedirect("admindashboard");
        } else {
        	//error message
        	request.setAttribute("errorMessage", "Ticket escalation failed.");

            // Use RequestDispatcher to include the error message in the forward
            RequestDispatcher dispatcher = request.getRequestDispatcher("ticketdetails?ticketId=" + ticketId);
            dispatcher.forward(request, response);
        }
	}

}
