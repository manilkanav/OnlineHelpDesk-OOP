<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>User Login</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<link href="css/styles.css" rel="stylsheet">
</head>
<body>
	<c:if test="${not empty error}">
     	<h1>${error}</h1>
     </c:if>
     <div class="container-fluid">
	 	<form class="mx-auto" action="ulogin" method="post">
	 	<h4 class="text-center">User Login</h4>
		 	<div class="mb-3">
		        <label for="username">Username:</label>
		        <input type="text" id="username" name="username" required placeholder="Username">
	        </div>
	        
	        <div class="mb-3">
		        <label for="password">Password:</label>
		        <input type="password" id="password" name="password" required placeholder="Password">
		    </div>
	        <button type="submit" class="btn btn-primary">Login</button>
	    </form>
	    <div><p>Not a registered user?<a href="userSignup.jsp">Sign up</a></p></div>
    </div>
	
     
</body>
</html>