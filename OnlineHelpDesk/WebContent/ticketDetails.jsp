<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ticket Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h5>Ticket Details</h5>
            </div>
            <div class="card-body">
                <h4 class="card-title">Subject: <c:out value="${ticket.subject}" /></h4>
                <p class="card-text">Description: <c:out value="${ticket.description}" /></p>
                <p class="card-text">Type: <c:out value="${ticket.type}" /></p>
                <p class="card-text">Created At: <c:out value="${ticket.createdAt}" /></p>
                
                <!-- Display attached images in a carousel -->
                <div id="imageCarousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach items="${refImages}" var="image" varStatus="loop">
                            <div class="carousel-item<c:if test='${loop.index == 0}'> active</c:if>">
                                <img src="<c:out value="${image.url}" />" class="d-block w-100" alt="Image">
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
			        <c:when test="${userType == 'admin' && admin.type == 'Tier-1' && ticket.status == 'Open'}">
			            <form action="escalate" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <button type="submit" class="btn btn-primary">Escalate</button>
			            </form>
			            <form action="assign" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <input type="hidden" name="ticketId" value="${agent.agentId}">
			                <button type="submit" class="btn btn-warning">Assign</button>
			            </form>
			        </c:when>
			        <c:when test="${userType == 'admin' && admin.type == 'Tier-2' && ticket.status == 'Escalated'}">
			            <form action="assign" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <input type="hidden" name="ticketId" value="${agent.agentId}">
			                <button type="submit" class="btn btn-primary">Assign</button>
			            </form>
			        </c:when>
			        <c:when test="${userType == 'user' && ticket.status == 'Open'}">
			            <form action="SelfResolveTicketServlet" method="post">
			                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
			                <button type="submit" class="btn btn-secondary">Self Resolve</button>
			            </form>
			            
			        </c:when>
			        <c:when test="${userType == 'user' && ticket.type != 'Open'}">
			            <button type="button" class="btn btn-secondary" disabled>Self Resolve</button>
			            
			        </c:when>
			    </c:choose>
            </div>
        </div>
        <!-- Comments section -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Comments</h5>
        </div>
        <div class="card-body">
            <!-- Comment input box at the top -->
            <form action="AddCommentServlet" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Add a comment..." name="commentText" required>
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">Add Comment</button>
                    </div>
                </div>
                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
            </form>

            <!-- Display user comments with delete buttons -->
            <c:forEach items="${comments}" var="comment">
                <div class="card mb-3">
                    <div class="card-header">
                        Comment by User
                    </div>
                    <div class="card-body">
                        <p class="card-text"><c:out value="${comment.commentText}" /></p>
                    </div>
                    <div class="card-footer text-right">
                        <form action="DeleteCommentServlet" method="post">
                            <input type="hidden" name="commentId" value="${comment.commentId}">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
    
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
