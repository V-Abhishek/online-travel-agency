<%-- 
    Document   : search-results
    Created on : Apr 15, 2020, 7:26:29 PM
    Author     : Abhishek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/current_date" prefix="dateformat" %>
<!DOCTYPE html>
<html>
    <style>
        body {
            background-color: #C4CBC8;
        }

        #customers {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
            align-content: center;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        a:link {
            background-color: #46BBD6;
            color: white;
            padding: 5px 5px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 2px;
        }

        a:hover, a:active {
            background-color: #58C9E3;
        }
        form {
            text-align: center;
        }

        input[type="submit"] {
            color: #fff !important;
            background-color: green;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        <h1 align="center">Flights Search Results</h1>
        <form action="search.htm" method="post" onsubmit="return validateForm();">
            <label>Flying from*</label>
            <input name="departureLocation" value="${requestScope.departureLocation}" placeholder="Enter departure location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only">
            <label>Going to*</label>
            <input name="arrivalLocation" value="${requestScope.arrivalLocation}" placeholder="Enter arrival location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only">
            <label>Date of Departure*</label>
            <input type="date" value="${requestScope.date}" name="date" min="<dateformat:CurrentDateFormat format='yyyy-MM-dd'/>" required>
            <span class="validity"></span>
            <input name="comingFrom" value="customer" type="hidden">
            <input type="submit" value="Find Flights">
        </form>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">Currently, there are no flights available for requested inputs</h2>
            </c:when>
            <c:otherwise>
                <table id="customers">
                    <thead>
                        <tr>
                            <th>flight Number</th>
                            <th>Date</th>
                            <th>Departure</th>
                            <th>Arrival</th>
                            <th>Departure Time</th>
                            <th>Duration(Hrs)</th>
                            <th>WiFi</th>
                            <th>Price($)</th>
                            <th>Book Flight</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestList}" var = "flight">
                            <c:if test="${flight.isAvailable == 'YES'}">
                                <c:if test="${flight.aircraft.hasWifi == 'YES'}">
                                    <tr class="wifiPresent">
                                    </c:if>
                                    <c:if test="${flight.aircraft.hasWifi == 'NO'}">
                                    <tr class="wifiNotPresent">
                                    </c:if>
                                    <td><c:out value="${flight.flightNumber}"/></td>
                                    <td><c:out value="${flight.date}"/></td>
                                    <td><c:out value="${flight.departureLocation}"/></td>
                                    <td><c:out value="${flight.arrivalLocation}"/></td>
                                    <td><c:out value="${flight.flightTime}"/></td>
                                    <td><c:out value="${flight.flightDuration}"/></td>
                                    <td><c:out value="${flight.aircraft.hasWifi}"/></td>
                                    <td><c:out value="${flight.price}"/></td>
                                    <td><a href="flight.htm?action=reserveTicket&id=${flight.flightId}">Make Reservation</a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                <input type="checkbox" id="wifiAvailable">
                <label for="wifiAvailable">WiFi Available</label>
            </c:otherwise>
        </c:choose>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/validate-search.js"></script>
    <script type="text/javascript" src="js/secondary-selectors.js"></script>
</html>