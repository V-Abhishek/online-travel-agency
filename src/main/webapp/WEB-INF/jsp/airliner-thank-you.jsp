<%-- 
    Document   : airliner-thank-you
    Created on : Mar 28, 2020, 12:17:03 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/form.css">
        <link rel="stylesheet" href="css/error.css">
        <title>Airliner Thank You</title>
    </head>
    <body>
        <div class="header">
            <h3>Thank you ${name}. Your airline ${requestScope.airliner} is under registration process and you will be updated once your account is activated.<br> Thank you for your interest in us.</h3>
            <a class="home" href="${pageContext.request.contextPath}">Go back to Home</a>
        </div>
    </body>
</html>