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
	<% User user = (User) request.getAttribute("user"); %>
	<p>
        <%= user.getId() %>
    </p>
    <img src="${userImageURL}" alt="Profile Image">
</body>
</html>