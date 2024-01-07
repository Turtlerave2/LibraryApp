<%--
  Created by IntelliJ IDEA.
  User: rob
--%>
<!-- changePassword.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
<h1>Change Password</h1>
<form action="controller" method="post">
    <input type="hidden" name="action" value="changePassword">
    Old Password: <input type="password" name="oldPassword"><br>
    New Password: <input type="password" name="newPassword"><br>
    Confirm New Password: <input type="password" name="newPasswordCopy"><br>
    <input type="submit" value="Change Password">
</form>
</body>
</html>