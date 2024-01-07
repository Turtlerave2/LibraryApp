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
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    First Name: <input type="text" name="fName"><br>
    Last Name: <input type="text" name="lName"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>