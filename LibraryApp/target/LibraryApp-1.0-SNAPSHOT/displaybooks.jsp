<%@ page import="business.Book" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Display All Books</title>
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
    <h1 class="mt-4 mb-4">All Books</h1>
    <%
        List<Book> bookList = (List<Book>) session.getAttribute("bookList");
        if (bookList != null && !bookList.isEmpty()) {
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
        <% for (Book book : bookList) { %>
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
            System.out.println("no books found");
        }
    %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
