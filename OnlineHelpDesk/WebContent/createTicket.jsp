<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="createticket" method="post" enctype="multipart/form-data">
        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject" required>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="5" required></textarea>
        
        <input type="hidden" name="status" value="Open"> <!-- Set status as Open by default -->
        
        <label for="type">Type:</label>
        <select id="type" name="type" required>
            <option value="Technical">Technical</option>
            <option value="Sales">Sales</option>
            <option value="Billing">Billing</option>
            <option value="General">General</option>
        </select>
        
        <label for="images">Attach Images:</label>
        <input type="file" id="images" name="images" accept="image/*" multiple>
        
        <input type="submit" value="Submit Ticket">
    </form>
</body>
</html>