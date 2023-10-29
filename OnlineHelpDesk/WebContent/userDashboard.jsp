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
<style>
        header {
            padding: 10px;
            margin: 0;
        }
        .nav-item.create-ticket {
            flex: 1;       
        }
        .logo{
        	font-size:45px;
        	font-weight: 700;
        }
    </style>
</head>
<body>
 <div style="background-color: white;">
        <header class="border-bottom">
            <div class="container-fluid">
                <nav class="navbar navbar-default navbar-fixed-top">
                    <a href="userdashboard" class="d-flex align-items-center link-body-emphasis text-decoration-none logo" >
                        HELPDESK
                    </a>


                    <ul class="nav d-flex create-ticket">
                        <li class="nav-item">
                            <a href="createTicket.jsp" class="nav-link link-body-emphasis" style="width: 200px; font-size:28px;font-weight: 500;" >Create Ticket</a>
                        </li>
                    </ul>

                  
                    <div class="dropdown">
                        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <% String userImageURL = (String) session.getAttribute("userImageURL"); %>
                            <img src="${userImageURL}" alt="mdo" width="60" height="60" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="#">Edit Profile</a></li>   
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout">Sign out</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
    </div>

	<% User user = (User) session.getAttribute("user"); %>
	
    <div class="container mt-4 ">
        <div class="row ">
            <div class="col-md-3">
                <a href="createTicket.jsp" class="btn btn-primary btn-block mb-3 mx-auto">Create Ticket</a>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${userTickets}" var="ticket">
                <div class="col-md-4 mb-3">
                    <div class="card ">
                        <div class="card-body ">
                            <h5 class="card-title">Ticket ID: ${ticket.ticketId}</h5>
                            <p class="card-text">Subject: ${ticket.subject}</p>
                            <p class="card-text">Status: ${ticket.status}</p>
                            <a href="ticketdetails?ticketId=${ticket.ticketId}" class="btn btn-success">View Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-yfv3eT/fn2LOzE+j8Fqzzt4WvS9z5n5s4Fe4r/JpwjBzOtc2XJwi5J5S9n0jkz5i"
        crossorigin="anonymous"></script>
</body>
</html>