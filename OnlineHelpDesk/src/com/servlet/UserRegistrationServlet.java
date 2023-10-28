package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.UserDAO;
import com.dbUtil.CommonUtils;
import com.model.User;

/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/UserRegistrationServlet")
@MultipartConfig
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Uploads" + File.separator + "profileImages";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO userdao = new UserDAO();
		String fileName;
		
		try {
			// retrieve form data
			String username = request.getParameter("username");
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String dateOfBirthStr = request.getParameter("date_of_birth");
			String gender = request.getParameter("gender");
			System.out.println(gender.length());
			
			Date dateOfBirth = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				// Parse String into a Date object
				dateOfBirth = formatter.parse(dateOfBirthStr);
				
			} catch(ParseException e) {
				e.printStackTrace();
			}
			
			
			// Process the uploaded profile image
			Part filePart = request.getPart("profile_img");
			
			if(filePart != null && filePart.getSize() > 0) {
				fileName = CommonUtils.generateUniqueFileName(filePart);
				
				String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		        File uploadDirectory = new File(uploadPath);
		        
		        if (!uploadDirectory.exists()) {
		            uploadDirectory.mkdirs();
		        }
		        
		        try (InputStream input = filePart.getInputStream()) {
		            Path filePath = Paths.get(uploadDirectory.getAbsolutePath(), fileName);
		            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
		        } catch(IOException e) {
		        	e.printStackTrace();
		        }
		        
			} else {
				// user doesn't upload any profile image 
				fileName = "default.png";
			}
			
	        User user = new User(username, firstName, lastName, password, email, phone, dateOfBirth, gender, fileName);
	        
	        // upload user to database
	        userdao.addUser(user);
	        
	        request.setAttribute("success", "User successfuly create");
	        
	        RequestDispatcher dis = request.getRequestDispatcher("userLogin.jsp");
	        
	        dis.forward(request, response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
        
		
	}

}
