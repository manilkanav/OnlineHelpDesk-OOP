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

import com.dao.AgentDAO;
import com.model.Admin;
import com.model.Agent;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/admindashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            // Admin is not logged in, redirect to the login page
            response.sendRedirect("adminLogin.jsp"); // Replace with the actual login page URL
            return;
        }
        
        AgentDAO agentdao = new AgentDAO();
        // Retrieve a list of all agents
        List<Agent> allAgents = agentdao.getAllAgents();
        request.setAttribute("allAgents", allAgents);

        // Forward the request to the admin dashboard page
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminDashboard.jsp"); // Replace with the actual JSP page
        dispatcher.forward(request, response);
	}

}
