<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Agent Login</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<style>
	body{
		background: linear-gradient(90deg,rgb(130, 40, 168)0%,rgb(135, 56, 199)30%,rgb(255, 255, 255)100%);
	}
	</style>
</head>
<body>
	<c:if test="${not empty error}">
     	<h1>${error}</h1>
     </c:if>
     <div class="container-fluid">
	 	<form class="mx-auto" action="ulogin" method="post">
	 	<h4 class="text-center">Agent Login</h4>
		 	<div class="mb-3">
		        <label for="username" class="form-label">Username:</label>
		        <input type="text" class="form-control" id="username" name="username" required placeholder="Username">
	        </div>
	        
	        <div class="mb-3">
		        <label for="password" class="form-label">Password:</label>
		        <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
		    </div>
	        <button type="submit" class="btn btn-primary mt-1" style="background:rgb(130, 40, 168)">Login</button>
	        <div><p>Not a registered user?<a href="userSignup.jsp">Sign up</a></p></div>
	    </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>