<%@ page import="business.User" %><%--
  Created by IntelliJ IDEA.
  User: rob
--%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<%
    // Retrieve the loggedInUser object from the request
    User loggedInUser = (User) request.getAttribute("loggedInUser");

    if (loggedInUser != null) {
%>
<p>Username: <%= loggedInUser.getUsername() %></p>
<p>Password: <%= loggedInUser.getPassword() %></p>
<p>First Name: <%= loggedInUser.getFirstName() %></p>
<!-- Add other user information fields as needed -->
<%
} else {
%>
<p>No user information found.</p>
<%
    }
%>
</body>
</html>