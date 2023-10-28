package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AgentDAO;
import com.model.Agent;

/**
 * Servlet implementation class CreateAgentServlet
 */
@WebServlet("/CreateAgentServlet")
public class CreateAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Process the agent creation form (POST request)
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String accountStatus = request.getParameter("accountStatus");
        String department = request.getParameter("department");
        String type = request.getParameter("type");
     
        
        Date dateOfBirth = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			// Parse String into a Date object
			dateOfBirth = formatter.parse(dateOfBirthStr);
			
		} catch(ParseException e) {
			e.printStackTrace();
		}

        // Create an Agent object
        Agent newAgent = new Agent(username, password, email, firstName, lastName, dateOfBirth, gender, accountStatus, department, type);
        // Include other agent details in the Agent object as needed

        // Save the new agent to the database (you need to implement the DAO)
        AgentDAO agentdao = new AgentDAO();
        int agentId = agentdao.createAgent(newAgent);

        if (agentId != -1) {
        	response.sendRedirect("AdminDashboardServlet");
        } else {
        	request.setAttribute("error", "agent creation failed");
        	RequestDispatcher dis = request.getRequestDispatcher("createAdmin.jsp");
        	
        	dis.include(request, response);
           
        }
	}

}
