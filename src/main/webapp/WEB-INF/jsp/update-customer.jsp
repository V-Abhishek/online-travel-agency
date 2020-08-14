<%-- 
    Document   : update-customer
    Created on : Apr 15, 2020, 3:37:17 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
    </head>
    <body>
        <div class="header">
            <h1>Update Details</h1>
        </div>
    <c:choose>
        <c:when test="${empty sessionScope.customer}">
            <h2 align="center">Something Went wrong!!</h2>
        </c:when>
        <c:otherwise>
            <div class="content">
                <form id="form_login" action="adminaction.htm" method="post">
                    <label>First Name*</label>
                    <input name="firstName" value="${sessionScope.customer.firstName}" placeholder="Enter your first name" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                    <label>Last Name*</label>
                    <input name="lastName" value="${sessionScope.customer.lastName}" placeholder="Enter your last name" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                    <label>Phone*</label>
                    <input name="phone" value="${sessionScope.customer.cellphone}" placeholder="Enter phone number" required="" type="tel" pattern="\d{10}$" title="xxxxxxxxxx">
                    <small>(format: xxxxxxxxxx)</small><br>
                    <label>Email Id*</label>
                    <input name="email" value="${sessionScope.customer.email}" readonly placeholder="Enter email address" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required="" type="email" title="xxx@xxx.xxx"><br>
                    <label>Street*</label>
                    <input name="street" value="${sessionScope.customer.street}" placeholder="Enter street" required="" type="text" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" title="Alphanumeric only">
                    <small>(Alphanumeric only. No special characters allowed)</small><br>
                    <label>City*</label>
                    <input name="city" value="${sessionScope.customer.city}" placeholder="Enter city name" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                    <label>State*</label>
                    <input name="state" value="${sessionScope.customer.state}" placeholder="Enter state name" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                    <label>Country*</label>
                    <input name="country" value="${sessionScope.customer.country}" placeholder="Enter country name" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                    <label>Zip Code*</label>
                    <input name="zip" value="${sessionScope.customer.zipCode}" placeholder="Enter zip code" required="" type="text" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" title="xxxxx-xxxx or xxxxx and numbers only">
                    <small>(format: xxxxx-xxxx or xxxxx)</small>
                    <input name="action" value="updateCustomer" type="hidden">
                    <p id="submission"><input type="submit" value="Update"></p>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>