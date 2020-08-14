<%-- 
    Document   : admin-view-bookings
    Created on : Apr 17, 2020, 5:24:48 AM
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
        <h1 align="center">MY BOOKINGS</h1>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">Currently, you do not have any bookings</h2>
            </c:when>
            <c:otherwise>
                <table id="customers">
                    <thead>
                        <tr>
                            <th>Ticket Number</th>
                            <th>flight Number</th>
                            <th>Departure Date</th>
                            <th>Departure Time</th>
                            <th>Departure</th>
                            <th>Arrival</th>
                            <th>Seat Number</th>
                            <th>Price</th>
                            <th>Booking Date and Time</th>
                            <th>View Ticket</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestList}" var = "ticket">
                            <tr>
                                <td><c:out value="${ticket.ticketNumber}"/></td>
                                <td><c:out value="${ticket.flightNumber}"/></td>
                                <td><c:out value="${ticket.departureDate}"/></td>
                                <td><c:out value="${ticket.departureTime}"/></td>
                                <td><c:out value="${ticket.departureLocation}"/></td>
                                <td><c:out value="${ticket.arrivalLocation}"/></td>
                                <td><c:out value="${ticket.seatNumber}"/></td>
                                <td><c:out value="${ticket.price}"/></td>
                                <td><c:out value="${ticket.bookingDate}"/></td>
                                <td><a href="pdfview.htm?id=${ticket.ticketId}">View Ticket</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>      
        </c:otherwise>
    </c:choose>
</body>
</html>