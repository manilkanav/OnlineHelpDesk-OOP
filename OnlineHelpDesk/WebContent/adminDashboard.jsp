<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Admin Dashboard</a>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <button class="btn btn-danger" onclick="location.href='logout'">Logout</button>
            </li>
        </ul>
    </nav>

    <div class="container mt-4">
        
        <h2>Agent Details</h2>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Department</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${allAgents}" var="agent">
                    <tr>
                        <td>${agent.id}</td>
                        <td>${agent.first_name} ${agent.last_name}</td>
                        <td>${agent.type}</td>
                        <td>${agent.account_status}</td>
                        <td>${agent.department}</td>
                        <td>
                            <form action="deleteagent" method="post">
                                <input type="hidden" name="agentId" value="${agent.id}">
                                <input type="submit" value="Delete" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    	<button class="btn btn-primary" onclick="location.href='createAgent.jsp'">Create Agent</button>
	
    </div>
	
	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
