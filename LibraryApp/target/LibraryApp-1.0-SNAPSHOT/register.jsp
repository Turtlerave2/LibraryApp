<%--
  Created by IntelliJ IDEA.
  User: rob
--%>
<!-- register.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Registration Page</h1>
<form action="controller" method="post">
    <input type="hidden" name="action" value="register">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword"><br>
    <label for="fName">First Name:</label>
    <input type="text" id="fName" name="fName"><br>
    <label for="lName">Last Name:</label>
    <input type="text" id="lName" name="lName"><br>
    <input type="submit" value="Register">
</form>
<p>Already have an account? <a href="login.jsp">Login here</a>.</p>
</body>
</html>