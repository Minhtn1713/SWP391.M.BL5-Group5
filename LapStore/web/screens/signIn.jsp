<%-- 
    Document   : signIn
    Created on : Aug 10, 2024, 10:35:45 PM
    Author     : lords
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>        
        <link rel="stylesheet" href="styles/signin.css" type="text/css"/>
        
    </head>
    <body>
        <div id="background">
            <img src="./img/banner.png" alt="Background">
        </div>

        <header>
            <a style="text-decoration: none; color: black" href="home"><h1>LapStore</h1></a>
        </header>

        <div class="container">
            <div class="login-box">
                <form action="sign-in" method="post">
                    <h2>Sign In</h2>
                    <%-- Display error message if available --%>
                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p>
                    </c:if>
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="submit" value="Sign In">
                </form>
                <a href="forgot-password">Forgot Password?</a> |
                <a href="sign-up">Sign Up</a>
            </div>
        </div>

        <footer>
            <p>Copyright © 2024 LAP STORE</p>
        </footer>
    </body>
</html>
