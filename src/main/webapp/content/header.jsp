<%-- 
    Document   : header
    Created on : Apr 3, 2020, 9:58:10 PM
    Author     : Abhishek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Travel</title>
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" type="text/css" href="css/admin-home.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <div class="header">
        <a href="${pageContext.request.contextPath}" class="logo"><img SRC="images/logo.png" alt="Logo" width=150 height=100></a>
        <span class="company">Travel the world</span>
        <c:choose>
            <c:when test="${(empty sessionScope.customer) and (empty sessionScope.airliner)and (empty sessionScope.admin)}">
                <div class="dropdown">
                    <button class="dropbtn">Customer</button>
                    <div class="dropdown-content">
                        <a onclick="document.getElementById('customer').style.display = 'block'" style="width:auto;">Customer Login</a>
                        <a href="customer-registration.html">Customer SignUp</a>
                    </div>
                </div>
                <div class="dropdown">
                    <button class="dropbtn">Airliner</button>
                    <div class="dropdown-content">
                        <a onclick="document.getElementById('airliner').style.display = 'block'" style="width:auto;">Airliner Login</a>
                        <a href="airliner-registration.html">Airliner SignUp</a>
                    </div>
                </div>
                <div id="customer" class="modal">
                    <form class="modal-content animate" action="login.htm" method="post">
                        <div class="container">
                            <h3><em>Customer Login</em></h3>
                            <label for="email"><b>Username</b></label>
                            <input name="email" placeholder="Enter your Email Id" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required="" type="email" title="xxx@xxx.xxx">
                            <label for="psw"><b>Password</b></label>
                            <input name="psw" placeholder="Enter password for your account" required="" type="password" title="Password must contain at least 6 characters.">
                            <input type="hidden" name="action" value="customerLogin">
                            <button type="submit">Login</button>
                        </div>
                        <div class="container" style="background-color:#f1f1f1">
                            <button type="button" onclick="document.getElementById('customer').style.display = 'none'" class="cancelbtn">Cancel</button>
                        </div>
                    </form>
                </div>
                <div id="airliner" class="modal">
                    <form class="modal-content animate" action="login.htm" method="post">
                        <div class="container">
                            <h3><em>Airliner Login</em></h3>
                            <label for="uname"><b>Username</b></label>
                            <input name="email" placeholder="Enter your Email Id" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required="" type="email" title="xxx@xxx.xxx">
                            <label for="psw"><b>Password</b></label>
                            <input name="psw" placeholder="Enter password for your account" required="" type="password">
                            <input type="hidden" name="action" value="airlinerLogin">
                            <button type="submit">Login</button>
                        </div>
                        <div class="container" style="background-color:#f1f1f1">
                            <button type="button" onclick="document.getElementById('airliner').style.display = 'none'" class="cancelbtn">Cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="dropdown">
                    <form action="index.htm" method="post">
                        <input type="hidden" name="action" value="logout"/>
                        <button class="logout">LogOut</button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <body>
