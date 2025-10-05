<%-- 
    Document   : logout
    Created on : 21 Sept 2025, 9:03:03 am
    Author     : ARSHAD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Invalidate the session to log the user out --%>
<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>

