<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Ticket Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
<c:choose>
	<c:when test="${userType == 'user'}">
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
 	</c:when>
    <c:when test="${userType == 'agent'}">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Agent Dashboard</a>
        <button class="btn btn-danger" onclick="location.href='logout'">Logout</button>
    	</nav>               
    </c:when>
    </c:choose>

    
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h5>Ticket Details</h5>
            </div>
            <div class="card-body">
                <h4 class="card-title">Subject: <c:out value="${ticket.subject}" /></h4>
                <p class="card-text">Description: <c:out value="${ticket.description}" /></p>
                <p class="card-text">Type: <c:out value="${ticket.type}" /></p>
                <p class="card-text">Created At: <c:out value="${ticket.createAt}" /></p>
                

                <div id="imageCarousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach items="${refImages}" var="image" varStatus="loop">
                            <div class="carousel-item<c:if test='${loop.index == 0}'> active</c:if>">
                                <img src="<c:out value="${image.path}" />" class="d-block w-100" alt="Image">
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#imageCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#imageCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                
			    <c:choose>
			        <c:when test="${userType == 'agent' && agent.type == 'Tier 1' && ticket.status == 'Open'}">
			            <form action="escalate" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <button type="submit" class="btn btn-primary">Escalate</button>
			            </form>
			            <form action="assignticket" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <input type="hidden" name="ticketId" value="${agent.id}">
			                <button type="submit" class="btn btn-warning">Assign</button>
			            </form>
			        </c:when>
			        <c:when test="${userType == 'agent' && agent.type == 'Tier-2' && ticket.status == 'Escalated'}">
			            <form action="assignticket" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <input type="hidden" name="ticketId" value="${agent.id}">
			                <button type="submit" class="btn btn-primary">Assign</button>
			            </form>
			        </c:when>
			        <c:when test="${userType == 'user' && ticket.status == 'Open'}">
			            <form action="resolve" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <button type="submit" class="btn btn-secondary">Self Resolve</button>
			            </form>
			            
			        </c:when>
			    </c:choose>
            </div>
        </div>

    <div class="card mt-4">
        <div class="card-header">
            <h5>Comments</h5>
        </div>
        <div class="card-body">

            <form action="addcomment" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Add a comment..." name="commentText" required>
                    <input type="hidden" name="ticketId" value="${ticket.ticketId}">
                    <input type="hidden" name="UserId" value="${user.id}">
                  
                    <div class="input-group-append">
                        <c:choose>
                                <c:when test="${userType == 'user'}">
                                    <button class="btn btn-primary" type="submit">Add Comment</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-primary" type="submit" disabled>Add Comment</button>
                                </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
            </form>


            <c:forEach items="${comments}" var="comment">
                <div class="card mb-3">
                    <div class="card-header">
                        Comment by User
                    </div>
                    <div class="card-body">
                        <p class="card-text"><c:out value="${comment.commentText}" /></p>
                    </div>
                    <div class="card-footer text-right">
                    	
                        <form action="deletecomment" method="post">
                            <input type="hidden" name="commentId" value="${comment.commentId}">
                            <input type="hidden" name="ticketId" value="${ticket.ticketId}">
                            <c:choose>
                    		<c:when test="${userType == 'user'}">
                            <button class="btn btn-danger" type="submit" disableds>Delete</button>
                            </c:when>
                            <c:otherwise>
                            <button class="btn btn-danger" type="submit">Delete</button>
                            </c:otherwise>
                            </c:choose>
                        </form>
                       
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
    
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
