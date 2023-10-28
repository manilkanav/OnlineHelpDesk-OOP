<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Online Helpdesk - Create Ticket</title>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/createticket.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4">Create Support Ticket</h1>
        <form action="createticket" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="subject">Subject:</label>
                <input type="text" class="form-control" id="subject" name="subject" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
            </div>
            
            <input type="hidden" name="status" value="Open">
            
            <div class="form-group">
                <label for="type">Type:</label>
                <select class="form-control" id="type" name="type" required>
                    <option value="Technical">Technical</option>
                    <option value="Sales">Sales</option>
                    <option value="Billing">Billing</option>
                    <option value="General">General</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="images">Attach Images:</label>
                <input type="file" class="form-control-file" id="images" name="images" accept="image/*" multiple>
            </div>
            
            <button type="submit" class="shadow-lg btn btn-primary">Submit Ticket</button>
        </form>
    </div>
	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
