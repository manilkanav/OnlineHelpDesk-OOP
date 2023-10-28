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
                
                <!-- Display buttons based on user type -->
                <c:choose>
                    <c:when test="${userType == 'admin' && ticket.type == 'Tier-1'}">
                        <button type="button" class="btn btn-primary">Resolve</button>
                        <button type="button" class="btn btn-warning">Assign</button>
                    </c:when>
                    <c:when test="${userType == 'admin' && ticket.type == 'Tier-2'}">
                        <button type="button" class="btn btn-primary">Assign</button>
                    </c:when>
                    <c:when test="${userType == 'user' && ticket.status == 'Open'}">
                        <button type="button" class="btn btn-secondary">Self Resolve</button>
                        <button type="button" class="btn btn-warning">Delete Ticket</button>
                    </c:when>
                    <c:when test="${userType == 'user' && ticket.type != 'Open'}">
                        <button type="button" class="btn btn-secondary" disabled>Self Resolve</button>
                        <button type="button" class="btn btn-secondary" disabled>Delete Ticket</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
