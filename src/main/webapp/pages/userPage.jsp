<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>User Form</title>
    <style>
        <%@include file='../css/styleUser.css' %>
    </style>
</head>
<body>

<table>
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
        </tr>
    </c:forEach>
</table>

<a href="<c:url value="/logout"/>">Logout</a>

</body>
</html>