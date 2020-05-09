<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Login Form</title>
    <style>
        <%@include file='css/styleReg.css' %>
    </style>
</head>
<body>

<form class="box" method="post" action="addUser">
    <h1>Registration</h1>
    <input type="text" name="role" placeholder="Role (User|Admin)">
    <input type="text" name="name" placeholder="Username">
    <input type="text" name="email" placeholder="E-mail">
    <input type="text" name="country" placeholder="Country">
    <input type="text" name="login" placeholder="Login">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Sign up">
</form>

<a href="loginPage.jsp">Go to login page</a>

</body>
</html>