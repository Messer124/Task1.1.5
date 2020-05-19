<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Login Form</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>

<form class="box" action="/login" method="post">
    <h1>Login</h1>
    <input type="text" name="login" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Login">
</form>

<ul>
    <li><a href="pages/registerPage.jsp">Go to register page</a></li>
</ul>

</body>
</html>
