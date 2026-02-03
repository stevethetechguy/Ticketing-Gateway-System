<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <h2>Login</h2>
    
    <div id="message"></div>

    <form id="loginForm">
        <div>
            <label>Email:</label>
            <input type="email" id="email" required>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" id="password" required>
        </div>
        <button type="submit">Login</button>
    </form>

    <p>Don't have an account? <a href="register.jsp">Register</a></p>

    <script>
        $(document).ready(function() {
            $("#loginForm").on("submit", function(e) {
                e.preventDefault();

                const loginData = {
                    email: $("#email").val(),
                    password: $("#password").val()
                };

                $.ajax({
                    url: "/api/users/auth/login",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(loginData),
                    success: function(response) {
                        $("#message").text("Login Success. Redirecting...");
                        window.location.href = "/dashboard.jsp";
                    },
                    error: function(xhr) {
                        $("#message").text("Error: Invalid credentials.");
                    }
                });
            });
        });
    </script>

</body>
</html>