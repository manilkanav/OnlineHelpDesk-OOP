package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AgentTicketAssignmentDAO;
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
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));

        AgentTicketAssignmentDAO atadao = new AgentTicketAssignmentDAO();
        
        atadao.deleteAssignmentsForTicket(ticketId);

        TicketDAO ticketDAO = new TicketDAO();
        boolean success = ticketDAO.setStatusResolve(ticketId);

        if (success) {
            // Ticket resolved successfully
            response.sendRedirect("ticketdetails?ticketId=" + ticketId);
        }
            
        else {
        	System.out.println("this ran");
            response.sendRedirect("ticketdetails?ticketId=" + ticketId); 
        }
	}

}
