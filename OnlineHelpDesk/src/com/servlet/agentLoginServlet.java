package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AgentDAO;
import com.model.Agent;

/**
 * Servlet implementation class agentLoginServlet
 */
@WebServlet("/aglogin")
public class agentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AgentDAO agentdao = new AgentDAO();
		Agent agent = agentdao.getAgentByUsername(username);
		
		if (agent != null && agent.getPassword().equals(password) && agent.getAccount_status() == "Active") {
            HttpSession session = request.getSession();
            session.setAttribute("agentnane", username);
            session.setAttribute("userType", "agent");
            
            response.sendRedirect("AgentDashboardServlet");
            
            
        } else {
            request.setAttribute("error", "authentication failed");
            
            RequestDispatcher dis = request.getRequestDispatcher("agentLogin.jsp");
            dis.include(request, response);
        }
	}

}
