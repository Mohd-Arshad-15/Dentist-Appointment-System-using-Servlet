<%-- 
    Document   : login
    Created on : 21 Sept 2025, 8:59:45 am
    Author     : ARSHAD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Login</title>
</head>
<body>
    <h2>Patient Login</h2>
    <!-- Display error message if login fails -->
    <p style="color:red;">${requestScope.error}</p>

    <form action="main" method="post">
        <input type="hidden" name="action" value="login">
        <p>Email: <input type="email" name="email" required></p>
        <p>Password: <input type="password" name="password" required></p>
        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
</body>
</html>

