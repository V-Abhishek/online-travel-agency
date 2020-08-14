<%-- 
    Document   : search-flights
    Created on : Apr 15, 2020, 5:56:42 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/current_date" prefix="dateformat" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Flights</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/aircraft-register.css">
    </head>
    <body>
        <div class="header">
            <h1>Find My Flights</h1>
        </div>
        <div class="content">
            <form action="search.htm" method="post" onsubmit="return validateForm();">
                <label>Flying from*</label>
                <input name="departureLocation" placeholder="Enter departure location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                <label>Going to*</label>
                <input name="arrivalLocation" placeholder="Enter arrival location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                <label>Date of Departure*</label>
                <input type="date" name="date" min="<dateformat:CurrentDateFormat format='yyyy-MM-dd'/>" required><br>
                <span class="validity"></span>
                <input name="comingFrom" value="customer" type="hidden">
                <p id="submission"><input type="submit" value="Find Flights"></p>
            </form>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/validate-search.js"></script>
</html>