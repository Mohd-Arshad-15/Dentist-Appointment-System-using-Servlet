/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ARSHAD
 */
package com.clinic.controller;

import com.clinic.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Main Servlet Controller for the Dentist Appointment System.
 * This file is structured to integrate seamlessly with a NetBeans IDE project.
 * The @WebServlet annotation automatically maps this servlet to the "/main" URL,
 * so no web.xml configuration is needed for this servlet.
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method. This is where all form data is processed.
     *
     * @param request The servlet request object, containing form data.
     * @param response The servlet response object, used for redirection or forwarding.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, jakarta.servlet.ServletException {
        
        // Retrieve the 'action' parameter from the submitted form to determine the task.
        String action = request.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            // Ensure the database connection is valid before proceeding.
            if (conn == null) {
                throw new ServletException("Database connection failed. Check server logs for details.");
            }

            // Use a switch statement to call the appropriate handler method based on the action.
            switch (action) {
                case "register" -> handleRegister(conn, request, response);
                case "login" -> handleLogin(conn, request, response);
                case "bookAppointment" -> handleBookAppointment(conn, request, response);
                default -> // As a fallback, redirect to a safe, known page.
                    response.sendRedirect("about.jsp");
            }
        } catch (SQLException e) {
            // Centralized error handling for any SQL issues.
            throw new ServletException("A database error occurred during the operation.", e);
        }
    }

    // --- HANDLER METHODS ---

    /**
     * Handles user registration.
     */
    private void handleRegister(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // SECURITY NOTE: Always hash passwords in a real application.
        String phone = request.getParameter("phone");

        // Use a database transaction to ensure data integrity. Both database inserts must succeed.
        conn.setAutoCommit(false);

        String userSql = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (PreparedStatement userStmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS)) {
            userStmt.setString(1, email);
            userStmt.setString(2, password);
            userStmt.executeUpdate();

            // Retrieve the auto-generated user ID to link with the patient record.
            try (ResultSet rs = userStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    String patientSql = "INSERT INTO patients (user_id, full_name, phone_number) VALUES (?, ?, ?)";
                    try (PreparedStatement patientStmt = conn.prepareStatement(patientSql)) {
                        patientStmt.setInt(1, userId);
                        patientStmt.setString(2, fullName);
                        patientStmt.setString(3, phone);
                        patientStmt.executeUpdate();
                    }
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            conn.commit(); // If all operations succeed, commit the changes to the database.
            response.sendRedirect("login.jsp?reg=success"); // Redirect to login page after successful registration.
        } catch (SQLException e) {
            conn.rollback(); // If any operation fails, roll back all changes.
            throw new ServletException("Registration database operation failed.", e);
        }
    }

    /**
     * Handles user login.
     */
    private void handleLogin(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, jakarta.servlet.ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT p.id, p.full_name FROM users u JOIN patients p ON u.id = p.user_id WHERE u.email = ? AND u.password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // User is authenticated, create a session.
                    HttpSession session = (HttpSession) request.getSession();
                    session.setAttribute("patientId", rs.getInt("id"));
                    session.setAttribute("patientName", rs.getString("full_name"));
                    response.sendRedirect("welcome.jsp"); // Redirect to the user's welcome page.
                } else {
                    // Authentication failed, forward back to the login page with an error message.
                    request.setAttribute("error", "Invalid email or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
    }

    /**
     * Handles appointment booking for a logged-in user.
     */
    private void handleBookAppointment(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, jakarta.servlet.ServletException {
        jakarta.servlet.http.HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist.

        // Security check: ensure a user is logged in before allowing them to book.
        if (session != null && session.getAttribute("patientId") != null) {
            int patientId = (int) session.getAttribute("patientId");
            String apptDate = request.getParameter("apptDate");
            String apptTime = request.getParameter("apptTime");
            String reason = request.getParameter("reason");

            String sql = "INSERT INTO appointments (patient_id, appointment_date, appointment_time, reason) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, patientId);
                stmt.setDate(2, Date.valueOf(apptDate));
                stmt.setTime(3, Time.valueOf(apptTime + ":00"));
                stmt.setString(4, reason);
                stmt.executeUpdate();

                // Forward to the same page with a success message.
                request.setAttribute("message", "Appointment booked successfully!");
                request.getRequestDispatcher("appointment.jsp").forward(request, response);
            }
        } else {
            // If no user is in session, redirect to the login page.
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * By default, GET requests are forwarded to the POST handler.
     * This can be useful for certain actions but is not used in this specific form-based application.
     * @param request
     * @param response
     * @throws com.clinic.controller.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to a safe page, as this servlet is designed for POST actions.
        response.sendRedirect("about.jsp");
    }

    /**
     * Returns a short description of the servlet.
     * This is an auto-generated method stub from NetBeans.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Main controller servlet for handling all user actions.";
    }
}

