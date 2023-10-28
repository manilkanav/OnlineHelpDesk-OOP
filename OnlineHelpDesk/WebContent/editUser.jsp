<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Edit User Profile</h2>
        <form action="edituser" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${user.first_name}" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${user.last_name}" required>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
        <form action="deleteuser" method="post">
            <input type="hidden" name="username" value="${user.username}">
            <button type="submit" class="btn btn-danger mt-3">Delete User</button>
        </form>
    </div>
</body>
</html>
