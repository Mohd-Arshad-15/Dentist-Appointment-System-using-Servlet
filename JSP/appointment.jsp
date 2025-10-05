<%-- 
    Document   : appointment
    Created on : 21 Sept 2025, 9:01:33 am
    Author     : ARSHAD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Book Appointment</title></head>
<body style="background-color: powderblue">
    <%-- Check if user is logged in. If not, redirect to login page. --%>
    <% if (session.getAttribute("patientName") == null) {
        response.sendRedirect("login.jsp");
        return; // Important to stop further processing of the page
    } %>
    
    <h2>Book an Appointment, ${sessionScope.patientName}</h2>
    <!-- Display success message after booking -->
    <p style="color:green;">${requestScope.message}</p>

    <form action="main" method="post">
        <input type="hidden" name="action" value="bookAppointment">
        <p>Date: <input type="date" name="apptDate" required></p>
        <p>Time: <input type="time" name="apptTime" required></p>
        <p>Reason for Visit: <textarea name="reason" rows="4" cols="50"></textarea></p>
        <button type="submit">Book Now</button>
    </form>
    <hr>
    <a href="logout.jsp">Logout</a>
</body>
</html>
