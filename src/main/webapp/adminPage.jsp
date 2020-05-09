<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Form</title>
    <style>
        <%@include file='css/styleAdmin.css' %>
    </style>
</head>
<body>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    String role = "";
    try {
        role = session.getAttribute("role").toString();
    } catch (NullPointerException e) {
        e.printStackTrace();
    }
    if (role.equals("user")) {
        response.sendRedirect("/usere");
    } else if (role.equals("") || !role.equals("admin")) {
        response.sendRedirect("/logout");
    }
%>

<form class="box" method="post" action="/admin">
    <h1>Edit user</h1>
    <input type="number" name="id" placeholder="ID"> <br>
    <input type="text" name="role" placeholder="Role"> <br>
    <input type="text" name="name" placeholder="Username"> <br>
    <input type="text" name="email" placeholder="E-mail"> <br>
    <input type="text" name="country" placeholder="Country"> <br>
    <input type="text" name="login" placeholder="Login"><br>
    <input type="password" name="password" placeholder="Password"><br>
    <input type="submit" value="Edit">
</form>

<table class="box" cellspacing='0'>
    <tr>
        <th>ID</th>
        <th>Role</th>
        <th>Name</th>
        <th>Email</th>
        <th>Country</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.role}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.country}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>
                <a href="/delete?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>


<a href="<c:url value="/logout"/>">Logout</a>

</body>
</html>