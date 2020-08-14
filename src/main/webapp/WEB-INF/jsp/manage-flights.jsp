<%-- 
    Document   : manage-flights
    Created on : Apr 13, 2020, 6:31:56 PM
    Author     : Abhishek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        input[type="submit"] {
            color: #fff !important;
            text-transform: uppercase;
            text-decoration: none;
            background: #4CAF50;
            padding: 20px 20px;
            border-radius: 50px;
            border: none;
            transition: all 0.4s ease 0s;
            position: relative;
            left: 470px;
            margin: 2px;
            width: 350px;
        }

        input[type="submit"]:hover {
            text-shadow: 0px 0px 6px rgba(255, 255, 255, 1);
            -webkit-box-shadow: 0px 5px 40px -10px rgba(0,0,0,0.57);
            -moz-box-shadow: 0px 5px 40px -10px rgba(0,0,0,0.57);
            transition: all 0.4s ease 0s;
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
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flights</title>
    </head>
    <body>
        <h1 align="center">FLIGHT SCHEDULE</h1>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">This aircraft currently does not have any flights schedules</h2>
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
                            <th>Availability Status</th>
                            <th>Price($)</th>
                            <th>Cancel Flight</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestList}" var = "flight">
                            <tr>
                                <td><c:out value="${flight.flightNumber}"/></td>
                                <td><c:out value="${flight.date}"/></td>
                                <td><c:out value="${flight.departureLocation}"/></td>
                                <td><c:out value="${flight.arrivalLocation}"/></td>
                                <td><c:out value="${flight.flightTime}"/></td>
                                <td><c:out value="${flight.flightDuration}"/></td>
                                <td><c:out value="${flight.isAvailable}"/></td>
                                <td><c:out value="${flight.price}"/></td>
                                <c:if test="${flight.isAvailable == 'YES'}">
                                    <td><a href="flight.htm?action=cancelFlight&id=${flight.flightId}">Cancel Flight</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>      
        </c:otherwise>
    </c:choose>
    <form action="flight.htm" method="post">
        <input type="hidden" name="action" value="addFlight">
        <input type="submit" value="Add Flight">
    </form>
</body>
</html>

