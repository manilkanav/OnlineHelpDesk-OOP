<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Online Helpdesk - Create Ticket</title>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/createticket.css" rel="stylesheet">
     <style>
        header {
            padding: 10px;
            margin: 0;
        }
        .nav-item.create-ticket {
            flex: 1;       
        }
        .logo{
        	font-size:45px;
        	font-weight: 700;
        }
    </style>
</head>
<body>
 <div style="background-color: white;">
        <header class="border-bottom">
            <div class="container-fluid">
                <nav class="navbar navbar-default navbar-fixed-top">
                    <a href="userdashboard" class="d-flex align-items-center link-body-emphasis text-decoration-none logo" >
                        HELPDESK
                    </a>


                    <ul class="nav d-flex create-ticket">
                        <li class="nav-item">
                            <a href="createTicket.jsp" class="nav-link link-body-emphasis" style="width: 200px; font-size:28px;font-weight: 500;" >Create Ticket</a>
                        </li>
                    </ul>

                  
                    <div class="dropdown">
                        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <% String userImageURL = (String) session.getAttribute("userImageURL"); %>
                            <img src="${userImageURL}" alt="mdo" width="60" height="60" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="editUser.jsp">Edit Profile</a></li>   
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout">Sign out</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
    </div>
    <div class="container mt-4">
        <h1 class="mb-4">Create Support Ticket</h1>
        <form action="createticket" method="post" enctype="multipart/form-data"style="margin-bottom:40px">
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
