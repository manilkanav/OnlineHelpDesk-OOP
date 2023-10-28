<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Agent Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Additional custom CSS for table styling */
        .table th, .table td {
            vertical-align: middle;
        }
        .green-text {
            color: green;
        }
        .blue-text {
            color: blue;
        }
        .orange-text {
            color: orange;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Agent Dashboard</a>
        <button class="btn btn-danger" onclick="location.href='logout'">Logout</button>
    </nav>

    <div class="container mt-4">
        <!-- Check if the agent is Tier-1 or Tier-2 and display the corresponding tickets -->
        <c:choose>
            <c:when test="${agent.type == 'Tier 1'}">
                <!-- Open Tickets Table for Tier-1 Agents -->
                <h2>Open Tickets</h2>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Ticket ID</th>
                            <th>Ticket Title</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterate through open tickets and display them -->
                        <c:forEach items="${openTickets}" var="ticket">
                            <tr>
                                <td>${ticket.ticketId}</td>
                                <td>${ticket.title}</td>
                                <td class="green-text">Open</td>
                                <td>
                                    <a href="ticketDetails?ticketId=${ticket.ticketId}" class="btn btn-info">View Details</a>
                                    <form action="escalateTicket" method="post">
                                        <input type="hidden" name="ticketId" value="${ticket.ticketId}">
                                        <button type="submit" class="btn btn-warning">Escalate</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${agent.type == 'Tier 2'}">
                <!-- Escalated Tickets Table for Tier-2 Agents -->
                <h2>Escalated Tickets</h2>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Ticket ID</th>
                            <th>Ticket Title</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterate through escalated tickets and display them -->
                        <c:forEach items="${escalatedTickets}" var="ticket">
                            <tr>
                                <td>${ticket.ticketId}</td>
                                <td>${ticket.title}</td>
                                <td class="orange-text">Escalated</td>
                                <td>
                                    <a href="ticketDetails?ticketId=${ticket.ticketId}" class="btn btn-info">View Details</a>
                                    <form action="resolveTicket" method="post">
                                        <input type="hidden" name="ticketId" value="${ticket.ticketId}">
                                        <button type="submit" class="btn btn-success">Resolve</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>

        <!-- Assigned Tickets Table (common for both Tier-1 and Tier-2 Agents) -->
        <h2>Assigned Tickets</h2>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Ticket ID</th>
                    <th>Ticket Title</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate through assigned tickets and display them -->
                <c:forEach items="${assignedTickets}" var="ticket">
                    <tr>
                        <td>${ticket.ticketId}</td>
                        <td>${ticket.title}</td>
                        <td class="blue-text">In Progress</td>
                        <td>
                            <a href="ticketDetails?ticketId=${ticket.ticketId}" class="btn btn-info">View Details</a>
                            <form action="resolveTicket" method="post">
                                <input type="hidden" name="ticketId" value="${ticket.ticketId}">
                                <button type="submit" class="btn btn-success">Resolve</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
