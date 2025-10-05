<%-- 
    Document   : welcome
    Created on : 21 Sept 2025, 9:02:20 am
    Author     : ARSHAD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Welcome</title></head>
<body style="background-color: powderblue">
    <%-- Redirect to login if user is not in session --%>
    <% if (session.getAttribute("patientName") == null) {
        response.sendRedirect("login.jsp");
        return;
    } %>

    <h1>Welcome, ${sessionScope.patientName}!</h1>
    <p>You have successfully logged in.</p>
    <a href="appointment.jsp">Book a New Appointment</a><br>
    <a href="logout.jsp">Logout</a>
</body>
</html>

