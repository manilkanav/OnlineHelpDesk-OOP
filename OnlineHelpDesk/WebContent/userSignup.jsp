<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Registration</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<link href="css/styles.css" rel="stylesheet">
	
	<style type="text/css">
		body{
	    background: rgb(37, 152, 95);
		}
		form{
		width: 60%;
		background-color: white;
		padding: 50px;
		border-radius: 20px;
		margin-top: 30px;
		margin-bottom: 40px;
		}
	</style>
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
     
	<h1 class="mx-auto" style="width: 300px; font-size:45px;font-weight: 700;margin-top: 50px;color: white">User Signup</h1>

    <div class="container-fluid">
	 	<form class="mx-auto" action="signup" method="post" enctype="multipart/form-data">
		 	<div class="mb-3">
		        <label for="username" class="form-label">Username:</label>
       			<input type="text" class="form-control" id="username" name="username" required >
	        </div>
	        
	        <div class="mb-3">
		    	<label for="first_name" class="form-label">First Name:</label>
        		<input type="text" class="form-control" id="first_name" name="first_name" required>     
		    </div>
		    
		     <div class="mb-3">
		     	<label for="last_name" class="form-label">Last Name:</label>
        		<input type="text" class="form-control" id="last_name" name="last_name">
		     </div>
		     	
		     <div class="mb-3">
		     	<label for="password" class="form-label">Password:</label>
        		<input type="password" class="form-control" id="password" name="password" required>
		     </div>
		     
		     <div class="mb-3">
		     	<label for="email" class="form-label">Email:</label>
        		<input type="email" class="form-control" id="email" name="email" required>
		     </div>
		     
		     <div class="mb-3">
		     	 <label for="phone" class="form-label">Phone:</label>
       			 <input type="text" class="form-control" id="phone" name="phone" required>
		     </div>
		     
		     <div class="mb-3">
		     	<label for="date_of_birth" class="form-label">Date of Birth:</label>
       			 <input type="date" class="form-control" id="date_of_birth" name="date_of_birth">
		     </div>
		     
		     <div class="mb-3">
		     	<label for="gender" class="form-label">Gender:</label>
        		<select id="gender" name="gender">
        		    <option value="male">Male</option>
            		<option value="female">Female</option>
            		<option value="other">Other</option>
        		</select>
		     </div>
		     
		     <div class="mb-3">
		     	<label for="profile_img" class="form-label">Profile Image:</label>
        		<input type="file" class="form-control" id="profile_img" name="profile_img">
		     </div>
		     		     
	        <button type="submit" class="btn btn-primary mt-1" style="background:rgb(37, 152, 95)">Sign Up</button>
	    </form>
    </div>
</body>
</html>