package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TicketDAO;
import com.dao.UserDAO;
import com.model.Ticket;
import com.model.User;

/**
 * Servlet implementation class UserDashboardServlet
 */
@WebServlet("/userdashboard")
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_DIRECTORY = "Uploads" + File.separator + "profileImages";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        String username = (String) session.getAttribute("username");

	        // Check if the user is logged in
	        if (username == null) {
	            // Redirect to the login page or display an error
	            response.sendRedirect("userLogin.jsp");
	            return;
	        }

	        // Fetch the user's tickets
	        UserDAO userdao = new UserDAO();
	        User user = userdao.getUserByUsername(username);

	        if (user != null) {
	            int userId = user.getId();
	            
	            String userImage = IMAGE_DIRECTORY + File.separator + user.getProfile_img();

	            TicketDAO ticketdao = new TicketDAO();
	            List<Ticket> userTickets = ticketdao.getTicketsByUserId(userId);

	            // Store the user's tickets in a request attribute
	            request.setAttribute("userImage", userImage);
	            request.setAttribute("userTickets", userTickets);
	        }

	        // Forward the request to the userDashboard.jsp
	        RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
	        dispatcher.forward(request, response);
	}

}
