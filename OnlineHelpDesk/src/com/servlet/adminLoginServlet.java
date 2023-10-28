package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDAO;
import com.model.Admin;

/**
 * Servlet implementation class adminLoginServlet
 */
@WebServlet("/adlogin")
public class adminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AdminDAO admindao = new AdminDAO();
		Admin admin = admindao.getAdminByUsername(username);
		
		if (admin != null && admin.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userType", "admin");
            session.setAttribute("admin", admin);
            
            response.sendRedirect("admindashboard");
            
            
        } else {
            request.setAttribute("error", "authentication failed");
            
            RequestDispatcher dis = request.getRequestDispatcher("adminLogin.jsp");
            dis.include(request, response);
        }
	}

}
