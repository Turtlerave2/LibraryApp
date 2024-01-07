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
<p>First Name: <%= loggedInUser.getFirstName() %></p>
<p>Last Name: <%= loggedInUser.getLastName() %></p>
<p>Username: <%= loggedInUser.getUsername() %></p>
<p>Password: <%= loggedInUser.getPassword() %></p>
<p>Email: <%= loggedInUser.getEmail() %></p>
<p>Address 1: <%= loggedInUser.getAddress1() %></p>
<p>Address 2: <%= loggedInUser.getAddress2() %></p>
<p>Eircode: <%= loggedInUser.getEircode() %></p>
<p>Phone Number: <%= loggedInUser.getPhoneNumber() %></p>
<p>Registration Date: <%= loggedInUser.getRegistrationDate() %></p>

<%
} else {
%>
<p>No user information found.</p>
<%
    }
%>
</body>
</html>