<%-- 
    Document   : invalid-input-error
    Created on : Apr 19, 2020, 1:22:02 AM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/error.css">
        <title>Error</title>
    </head>
    <body>
        <div>
            <h1>We're sorry, but something wrong with your input. Please recheck it.</h1>
            <a class="home" href="${pageContext.request.contextPath}">Go back to Home</a>
        </div>
    </body>
</html>

