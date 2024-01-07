<%@ page import="business.User" %><%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 07/01/2024
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home page</title>
</head>
<body>
<h1>Hello World!</h1>
<%
    User u = (User)session.getAttribute("user");
    if(u == null){
%>
<a href="controller?action=Showlogin">Login</a><br/>
<a href="controller?action=Showregister">Register</a>
<%}else{
%>
<div> You are logged in!</div>
<a href="controller?action=displayallbooks">Show all books</a><br/>
<a href="controller?action=Displayborrowbooks">Borrow a book</a>
<a href="controller?action=Showsearchbooks">Search for book</a>

<a>Add the href to the other actions?</a>
<%
    }
%>
<h1>Maybe add more stuff to this page or link to </h1>

</body>
</html>
