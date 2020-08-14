<%-- 
    Document   : admin-login
    Created on : Apr 11, 2020, 2:51:03 AM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administrator Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
                background-color: #C4CBC8;
            }
            form {
                border: 2px solid #f1f1f1;
                width: 500px;
                position: relative;
                left: 380px
            }

            input[type=email], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }
        </style>
    </head>
    <body>
        <h2 align="center">Administrator Login</h2>
        <form action="login.htm" method="post">
            <div class="imgcontainer">
                <img src="images/avatar.png" alt="Avatar" class="avatar" height="200" width="200">
            </div>
            <div class="container">
                <label for="email"><b>Username</b></label>
                <input type="email" title="xxx@xxx.xxx" placeholder="Enter Username" name="email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required="">
                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>
                <input type="hidden" name="action" value="adminLogin">
                <button type="submit">Login</button>
            </div>
        </form>
    </body>
</html>
