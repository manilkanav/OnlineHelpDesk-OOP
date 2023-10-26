package com.servlet;

import java.io.File;
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
 * Servlet implementation class userLoginServlet
 */
@WebServlet("/userLoginServlet")
public class userLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_DIRECTORY = "Uploads" + File.separator + "profileImages";
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDAO userdao = new UserDAO();
		User user = userdao.getUserByUsername(username);
		
		if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.setAttribute("user", user);
            
            String imageUrl = request.getServletContext().getRealPath(IMAGE_DIRECTORY) + File.separator + user.getProfile_img();
            request.setAttribute("userImageURL", imageUrl);
            
            RequestDispatcher dis = request.getRequestDispatcher("userDashboard.jsp");
            dis.forward(request, response);
            
            
        } else {
            request.setAttribute("error", "authentication failed");
            
            RequestDispatcher dis = request.getRequestDispatcher("userLogin.jsp");
            dis.include(request, response);
        }
			
	}

}
