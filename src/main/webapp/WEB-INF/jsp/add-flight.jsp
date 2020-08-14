<%-- 
    Document   : add-flight
    Created on : Apr 13, 2020, 8:13:47 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/current_date" prefix="dateformat" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Flight Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/aircraft-register.css">
    </head>
    <body>
        <div class="header">
            <h1>Register Flights</h1>
        </div>
        <div class="content">
            <form action="flight.htm" method="post" onsubmit="return validateForm();">
                <label>Flight Number*</label>
                <input name="flightNumber" placeholder="Enter flight number" required="" type="text" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" title="Alphanumeric only"><br>
                <label>Departure Location*</label>
                <input name="departureLocation" placeholder="Enter departure location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                <label>Arrival Location*</label>
                <input name="arrivalLocation" placeholder="Enter arrival location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                <label>Price*</label>
                <input name="price" placeholder="Enter price of the ticket" required="" type="text" pattern="[1-9]\d*(\.\d+)?$" title="Whole digits or Decimal numbers only">
                <small>(In dollars)(Format: xxxx or xxx.x)</small><br>
                <label>Duration of Flight*</label>
                <input name="flightDuration" placeholder="Enter duration of the journey" required="" type="text" pattern="[1-9]\d*(\.\d+)?$" title="Whole digits or Decimal numbers only">
                <small>(In hours)(Format:xx or xxx.x)</small><br>
                <label>Date of Departure*</label>
                <input type="date" name="date" min="<dateformat:CurrentDateFormat format='yyyy-MM-dd'/>" required><br>
                <span class="validity"></span>
                <label>Departure Time*</label>
                <input type="time" name="start" required><small>(Format: 24Hrs)</small>
                <input name="action" value="registerFlight" type="hidden">
                <p id="submission"><input type="submit" value="Add Flight"></p>
            </form>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/validate-flight.js"></script>
</html>
