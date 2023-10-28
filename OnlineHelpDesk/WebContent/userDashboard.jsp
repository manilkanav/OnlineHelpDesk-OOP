<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.model.User"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Dashboard</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<link href="css/styles.css" rel="stylsheet">
</head>
<body>	
     
	<% User user = (User) session.getAttribute("user"); 
	   String userImageURL = (String) session.getAttribute("userImageURL");%>
	<p>
        <%= user.getId() %>
    </p>
    
    <img src="<%= userImageURL %>" alt="Profile Image">
    <a href="createTicket.jsp">Create Ticket</a>
    
    
    <ul>
        <c:forEach items="${userTickets}" var="ticket">
            <li>
                Ticket ID: ${ticket.id}<br>
                Subject: ${ticket.subject}<br>
                Status: ${ticket.status}<br>
                <!-- "View Details" button to view ticket details -->
                <form action="ViewTicketDetailsServlet" method="get">
                    <input type="hidden" name="ticketId" value="${ticket.ticket_id}">
                    <button type="submit">View Details</button>
                </form>
            </li>
        </c:forEach>
    </ul>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>