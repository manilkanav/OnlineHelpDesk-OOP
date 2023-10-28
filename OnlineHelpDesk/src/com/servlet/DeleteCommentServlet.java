package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommentDAO;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/deletecomment")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// You need to retrieve the comment ID from the request parameters.
        int commentId = Integer.parseInt(request.getParameter("commentId"));

        CommentDAO commentDAO = new CommentDAO();
        boolean success = commentDAO.deleteComment(commentId);

        if (success) {
            response.sendRedirect("ticketDetails.jsp?ticketId=" + request.getParameter("ticketId"));
        } 
	}

}
