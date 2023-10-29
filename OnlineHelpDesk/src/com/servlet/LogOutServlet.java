package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userType = (String) session.getAttribute("userType");
        if (session != null) {
            session.invalidate();
        }
        
        if (userType == "user") {
        	response.sendRedirect("userLogin.jsp");
        }
        
        else if (userType == "agent") {
        	response.sendRedirect("agentLogin.jsp");
        }
        else {
        	response.sendRedirect("adminLogin.jsp");
        }
    }
}
