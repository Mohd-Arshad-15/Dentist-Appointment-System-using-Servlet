<%-- 
    Document   : register
    Created on : 21 Sept 2025, 9:00:36 am
    Author     : ARSHAD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head><title>Register</title></head>
<body>
    <h2>Create a New Account</h2>
    <form action="main" method="post">
        <input type="hidden" name="action" value="register">
        <p>Full Name: <input type="text" name="fullName" required></p>
        <p>Email: <input type="email" name="email" required></p>
        <p>Password: <input type="password" name="password" required></p>
        <p>Phone: <input type="tel" name="phone"></p>
        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>

