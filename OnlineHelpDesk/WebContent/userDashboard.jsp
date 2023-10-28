<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.model.User"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
</head>
<body>
	<% User user = (User) session.getAttribute("user"); %>
	<p>
        <%= user.getId() %>
    </p>
    
    <% String userImageURL = (String) session.getAttribute("userImageURL"); %>
    <img src="${userImageURL}" alt="Profile Image">
    <a href="createTicket.jsp">Create Ticket</a>
    
    
    <ul>
        <c:forEach items="${userTickets}" var="ticket">
            <li>
                Ticket ID: ${ticket.id}<br>
                Subject: ${ticket.subject}<br>
                Status: ${ticket.status}<br>
                <!-- "View Details" button to view ticket details -->
                <form action="ViewTicketDetailsServlet" method="get">
                    <input type="hidden" name="ticketId" value="${ticket.id}">
                    <button type="submit">View Details</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>