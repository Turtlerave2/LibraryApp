<%--
  Created by IntelliJ IDEA.
  User: rob
--%>
<!-- register.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h1>Registration Form</h1>
<form action="controller" method="post">
    <label for="username">Username: </label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password: </label>
    <input type="password" id="password" name="password" required><br>

    <label for="fName">First Name: </label>
    <input type="text" id="fName" name="fName" required><br>

    <label for="lName">Last Name: </label>
    <input type="text" id="lName" name="lName" required><br>

    <label for="email">Email: </label>
    <input type="email" id="email" name="email" required><br>

    <label for="address1">Address Line 1: </label>
    <input type="text" id="address1" name="address1"><br>

    <label for="address2">Address Line 2: </label>
    <input type="text" id="address2" name="address2"><br>

    <label for="eircode">Eircode: </label>
    <input type="text" id="eircode" name="eircode"><br>

    <label for="phoneNumber">Phone Number: </label>
    <input type="text" id="phoneNumber" name="phoneNumber"><br>

    <input type="submit" value="register">
</form>
</body>
</html>
