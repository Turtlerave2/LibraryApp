<%@ page import="business.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLOutput" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results By Title</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 20px;
        }
        .book-table th {
            text-transform: capitalize;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mt-4 mb-4">Books Found By Title</h1>
    <%
        List<Book> foundBooks = (List<Book>) session.getAttribute("foundbook");
        if (foundBooks != null && !foundBooks.isEmpty()) {
    %>
    <table class="table table-bordered table-striped book-table">
        <thead class="thead-dark">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Publication Year</th>
            <th>Genre</th>
            <th>Total Copies</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <% for (Book book : foundBooks) { %>
        <tr>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthorID() %></td>
            <td><%= book.getISBN() %></td>
            <td><%= book.getPublicationYear() %></td>
            <td><%= book.getGenreID() %></td>
            <td><%= book.getTotalCopies() %></td>
            <td><%= book.getDescription() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <%
        } else {
            System.out.println("no book");
        }
    %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

