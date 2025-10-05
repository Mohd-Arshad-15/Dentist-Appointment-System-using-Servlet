# Dentist-Appointment-System-using-Servlet


 🦷 Dentist Appointment Booking System (JSP + Servlet)
A web-based application for managing dental appointments, built with Java Servlets, JSP, and MySQL. This project allows patients to book appointments and enables administrators to manage schedules and patient data efficiently.To demonstrating my skill in java as my first project in git hub

📌 Features
👤 Dual User Roles: Separate login and dashboard functionalities for Patients and Admin/Dentist.

🔐 Secure Authentication: Robust login and registration system for users.

📊 User-Specific Dashboards:

Patient Dashboard: Book new Appointments.

Admin Dashboard: View all appointments, manage patient records, and approve or cancel bookings.

⚙️ Appointment Management: Admins have full CRUD (Create, Read, Update, Delete) capabilities over appointments.

🖥️ Responsive UI: A clean and simple user interface built with HTML, CSS, and Bootstrap.

📂 Project Structure

This project follows a standard Java Web Application structure.

DentistAppointmentSystem/
├── src/main/java/
│   └── com/yourpackage/
│       ├── controller/
│       │   └── MainServlet.java
│       └── util/
│           └── DBConnection.java
├── src/main/webapp/
│   ├── login.jsp
│   ├── register.jsp
│   ├── about.jsp
│   ├── appointment.jsp
│   ├── welcome.jsp         │   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── lib/
│   │       └── mysql-connector-j-8.x.x.jar  ```

---
🚀 Getting Started
Follow these steps to get the project up and running on your local machine.

1️⃣ Clone this repository

Bash

git clone https://github.com/your-username/Dentist-Appointment-System.git
cd Dentist-Appointment-System
2️⃣ Set up the Database

Make sure you have MySQL installed and running.

Create a new database.

SQL

CREATE DATABASE dentist_db;
Import the database.sql file into your newly created database to set up the required tables.

Update the database connection details (URL, username, password) in the appropriate DAO or connection utility class (e.g., DBConnection.java).

3️⃣ Run the application

Open the project in your favorite Java IDE (like Eclipse, IntelliJ IDEA, or NetBeans).

Configure an Apache Tomcat Server (version 9 or newer is recommended).

Build the project and deploy it to your Tomcat server.

Open your web browser and navigate to:

http://localhost:8080/your-project-name


🖼️ Screenshots

🔹 Admin Dashboard
<img width="1403" height="723" alt="image" src="https://github.com/user-attachments/assets/5144da1b-a857-411f-b7fd-d15973919349" />

🔹 Appointment Booking Form
<img width="1163" height="597" alt="image" src="https://github.com/user-attachments/assets/86b9b881-9e9c-4247-8b8c-e0575dd19bce" />




Getty Images

🛠️ Technology Stack
Frontend: HTML, CSS, JavaScript, JSP, Bootstrap

Backend: Java Servlets

Database: MySQL

Server: Apache Tomcat

Build Tool: Apache Maven (optional, can be built without it)






