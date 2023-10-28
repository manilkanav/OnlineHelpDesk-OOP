package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AgentTicketAssignmentDAO;
import com.dao.TicketDAO;
import com.model.Agent;
import com.model.Ticket;

/**
 * Servlet implementation class AgentDashboardServlet
 */
@WebServlet("/AgentDashboardServlet")
public class AgentDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Agent agent = (Agent) session.getAttribute("agent");

        if (agent == null) {
            // Agent is not logged in, redirect to the login page
            response.sendRedirect("agentLogin.jsp"); // Replace with the actual login page URL
            return;
        }
        
        TicketDAO ticketdao = new TicketDAO();
        AgentTicketAssignmentDAO assignedDAO = new AgentTicketAssignmentDAO();

        // Check agent's type (Tier-1 or Tier-2)
        if (agent.getType().equals("Tier 1")) {
            // Agent is Tier-1, retrieve and display open tickets
            List<Ticket> tickets = ticketdao.getOpenTickets();
            request.setAttribute("tickets", tickets);
        } else if (agent.getType().equals("Tier 2")) {
            // Agent is Tier-2, retrieve and display escalated tickets
            List<Ticket> tickets = ticketdao.getEscalatedTickets();
            request.setAttribute("escalatedTickets", tickets);
        }

        // Retrieve all tickets assigned to the agent
        List<Ticket> assignedTickets = assignedDAO.getAssignedTickets(agent.getId());
        request.setAttribute("assignedTickets", assignedTickets);

        // Forward the request to the agent dashboard page
        RequestDispatcher dispatcher = request.getRequestDispatcher("agentDashboard.jsp"); // Replace with the actual JSP page
        dispatcher.forward(request, response);
	}

}
