<%-- 
    Document   : update-aircraft
    Created on : Apr 13, 2020, 4:31:47 AM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Aircraft Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/aircraft-register.css">
    </head>
    <body>
        <div class="header">
            <h1>Register Your Aircrafts</h1>
        </div>
        <div class="content">
            <form action="airliner.htm" method="post" onsubmit="return validateForm();">
                <label>Aircraft Serial Number*</label>
                <input name="serialNumber" placeholder="Enter aircraft serial number" required="" type="text" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" title="Alphanumeric only"><br>
                <label>Aircraft Make*</label>
                <input name="name" placeholder="Enter aircraft make" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                <label>Model*</label>
                <input name="model" placeholder="Enter aircraft model" required="" type="text" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" title="Alphanumeric only"><br>
                <label>Entertainment equipped?</label>
                <input type="radio" name="entertainment" value="YES" required/>Yes  <input type="radio" name="entertainment" value="NO" required/>No<br>
                <label>WiFi equipped?</label>
                <input type="radio" name="wifi" value="YES" required/>Yes  <input type="radio" name="wifi" value="NO" required/>No<br>
                <input name="action" value="registerAircraft" type="hidden">
                <p id="submission"><input type="submit" value="Add Aircraft"></p>
            </form>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/validate-aircraft.js"></script>
</html>
