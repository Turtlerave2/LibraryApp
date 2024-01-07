<%--
  Created by IntelliJ IDEA.
  User: rob
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Profile</title>
</head>
<body>
<!-- editProfile.jsp -->
<form action="updateProfile" method="post">
    First Name: <input type="text" name="firstName" value="${loggedInUser.firstName}"><br>
    Last Name: <input type="text" name="lastName" value="${loggedInUser.lastName}"><br>
    Username: <input type="text" name="username" value="${loggedInUser.username}" readonly><br>
    Email: <input type="email" name="email" value="${loggedInUser.email}"><br>
    Address Line 1: <input type="text" name="address1" value="${loggedInUser.address1}"><br>
    Address Line 2: <input type="text" name="address2" value="${loggedInUser.address2}"><br>
    Eircode: <input type="text" name="eircode" value="${loggedInUser.eircode}"><br>
    Phone Number: <input type="text" name="phoneNumber" value="${loggedInUser.phoneNumber}"><br>
    <input type="submit" value="Save Changes">
</form>


<form action="changePassword.jsp" method="get">
    <input type="submit" value="Change Password">
</form>
</body>
</html>
