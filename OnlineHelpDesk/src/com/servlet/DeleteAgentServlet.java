package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AgentDAO;

/**
 * Servlet implementation class DeleteAgentServlet
 */
@WebServlet("/deleteagent")
public class DeleteAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");

        // Check if the user has admin privileges
        HttpSession session = request.getSession();
        String accountType = (String) session.getAttribute("accountType");

        if (accountType != null && accountType.equals("admin")) {
            AgentDAO agentDAO = new AgentDAO();
            boolean success = agentDAO.deleteAgentByUsername(username);

            if (success) {
                // Agent deleted successfully
                response.sendRedirect("adminDashboard.jsp"); // Redirect to admin dashboard or another appropriate page
            } else {
                // Handle error, perhaps redirect to an error page
                response.sendRedirect("errorPage.jsp"); // Change this to an appropriate error page
            }
        } else {
            // Handle unauthorized access (not an admin)
            response.sendRedirect("accessDenied.jsp"); // Redirect to an access denied page
        }
	}

}
