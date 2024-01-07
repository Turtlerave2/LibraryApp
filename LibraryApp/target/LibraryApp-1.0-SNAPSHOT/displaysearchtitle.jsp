<%--
  Created by IntelliJ IDEA.
  User: desti

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Book by Title</title>
</head>
<body>
<div class="container">
    <h1>Search Book by Title</h1>
    <form action="searchbooks" method="get">
        <div class="form-group">
            <label for="titleInput">Enter Title:</label>
            <input type="text" class="form-control" id="titleInput" name="Title" placeholder="Enter book title">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</div>

<!-- Bootstrap CSS and jQuery -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>