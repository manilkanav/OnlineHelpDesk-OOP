package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.model.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the user's ID from the request parameter
		HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Use the UserDAO to fetch the user's details by ID
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            // Set the user object as an attribute to be used in the JSP
            request.setAttribute("user", user);

            // Forward the request to the editUser.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Could not load customer");
            RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
            dispatcher.include(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			
			String email = request.getParameter("email");
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");

	        UserDAO userdao = new UserDAO();
	        User user = userdao.getUserByUsername(username);
	        
	        user.setEmail(email);
	        user.setFirst_name(firstName);
	        user.setLast_name(lastName);

	        boolean updated = userdao.updateUser(user);
	        
	        if(updated) {
	        	request.setAttribute("sucess", "user updated succesfully");
	        	
	        	RequestDispatcher dis = request.getRequestDispatcher("UserDashboardSerlvet");
	        	dis.forward(request, response);
	        }
	}

}
