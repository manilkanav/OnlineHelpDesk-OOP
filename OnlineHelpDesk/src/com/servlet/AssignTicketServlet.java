package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AgentTicketAssignmentDAO;

/**
 * Servlet implementation class AssignTicketServlet
 */
@WebServlet("/assignticket")
public class AssignTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        int agentId = Integer.parseInt(request.getParameter("agentId"));

        
        AgentTicketAssignmentDAO assignmentDAO = new AgentTicketAssignmentDAO();
        boolean success = assignmentDAO.assignTicketToAgent(agentId, ticketId);

        if (success) {
         // Set a success message
            request.setAttribute("successMessage", "Ticket assigned successfully.");

            // Use RequestDispatcher to include the success message in the forward
            RequestDispatcher dispatcher = request.getRequestDispatcher("ticketDetails.jsp?ticketId=" + ticketId);
            dispatcher.forward(request, response);
        } else {
        	//error message
        	request.setAttribute("errorMessage", "Ticket assignment failed.");

            // Use RequestDispatcher to include the error message in the forward
            RequestDispatcher dispatcher = request.getRequestDispatcher("ticketDetails.jsp?ticketId=" + ticketId);
            dispatcher.forward(request, response);
        }
	}

}
