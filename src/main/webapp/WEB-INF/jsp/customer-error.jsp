<%-- 
    Document   : customer-error
    Created on : Apr 15, 2020, 5:16:13 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/error.css">
        <title>Error</title>
    </head>
    <body>
        <div>
            <h1>We're sorry, but something went wrong.</h1>
            <c:choose>
                <c:when test="${empty message}">
                    <h2>Your request did not go through well.</h2>
                </c:when>
                <c:otherwise>
                    <h2>${message}</h2>
                </c:otherwise>
            </c:choose>
            <a class="home" href="${pageContext.request.contextPath}/customer.htm">Go back to Customer Home</a>
        </div>
    </body>
</html>