package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.RefImageDAO;
import com.dao.TicketDAO;
import com.dbUtil.CommonUtils;
import com.model.RefImage;
import com.model.Ticket;
import com.model.User;

/**
 * Servlet implementation class CreateTicketServlet
 */

@WebServlet("/CreateTicketServlet")
@MultipartConfig
public class CreateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Uploads" + File.separator + "TicketRefImg";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("userLogin.jsp");
		}
		
		// Get data from Form
		String subject = request.getParameter("subject");
		String description = request.getParameter("description");
		String status = "Open";
		String type = request.getParameter("type");
		
		Ticket ticket = new Ticket(user.getId(), subject, description, status, type);
		
		// Add ticket to database
		TicketDAO ticketdao = new TicketDAO();
		int ticketId = ticketdao.createTicket(ticket);
		
		// Handle image uploads
		Part filePart = request.getPart("images");
		
		if (filePart != null) {
            Collection<Part> parts = request.getParts();
            RefImageDAO refimagedao = new RefImageDAO();

            for (Part part : parts) {
            	String fileName = CommonUtils.generateUniqueFileName(part);

                if (fileName != null && !fileName.isEmpty()) {
                	String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
    		        File uploadDirectory = new File(uploadPath);
    		        
    		        if (!uploadDirectory.exists()) {
    		            uploadDirectory.mkdirs();
    		        }
 
    		        try (InputStream input = filePart.getInputStream()) {
    		            Path filePath = Paths.get(uploadDirectory.getAbsolutePath(), fileName);
    		            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
    		            
    		            RefImage referenceImage = new RefImage(ticketId, filePath.toString());
                        refimagedao.addRefImage(referenceImage);
    		            
    		        } catch(IOException e) {
    		        	e.printStackTrace();
    		        }
                }
            }
            
            List<Ticket> userTickets = ticketdao.getTicketsByUserId(user.getId());
            request.setAttribute("userTickets", userTickets);
            
            RequestDispatcher dis = request.getRequestDispatcher("userDashboard.jsp");
            dis.forward(request, response);
        }
		
		
		
	}

}
