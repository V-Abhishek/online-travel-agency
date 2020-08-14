<%-- 
    Document   : book-ticket
    Created on : Apr 16, 2020, 1:46:24 AM
    Author     : Abhishek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Reserve Ticket</h1>
        </div>
        <c:choose>
            <c:when test="${empty flight}">
                <h2 align="center">Currently, the requested flight is not available</h2>
            </c:when>
            <c:otherwise>
                <div class="content">
                    <form action="seat.htm" method="post" onsubmit="return validateForm();">
                        <label>Flight Number*</label>
                        <input name="flightNumber" value="${flight.flightNumber}" readonly placeholder="Enter flight number" required="" type="text" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" title="Alphanumeric only"><br>
                        <label>Departure Location*</label>
                        <input name="departureLocation" value="${flight.departureLocation}" readonly placeholder="Enter departure location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                        <label>Arrival Location*</label>
                        <input name="arrivalLocation" value="${flight.arrivalLocation}" readonly placeholder="Enter arrival location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only"><br>
                        <label>Price*</label>
                        <input name="price" value="${flight.price}" readonly placeholder="Enter price of the ticket" required="" type="text" pattern="[1-9]\d*(\.\d+)?$" title="Whole digits or Decimal numbers only">
                        <small>(In dollars)</small><br>
                        <label>Duration of Flight*</label>
                        <input name="flightDuration" value="${flight.flightDuration}" readonly placeholder="Enter duration of the journey" required="" type="text" pattern="[1-9]\d*(\.\d+)?$" title="Whole digits or Decimal numbers only">
                        <small>(Hrs)</small><br>
                        <label>Date of Departure*</label>
                        <input type="date" name="date" value="${flight.date}" readonly min="<dateformat:CurrentDateFormat format='yyyy-MM-dd'/>" required><br>
                        <span class="validity"></span>
                        <label>Departure Time*</label>
                        <input type="time" name="start" value="${flight.flightTime}" readonly required><small>(Format: 24Hrs)</small><br>
                        <label>Seat Type*</label>
                        <select id="seattype" name="seatType" required>
                            <option selected disabled>Please select a seat type</option>
                            <option value="w">Window</option>
                            <option value="a">Aisle</option>
                            <option value="m">Middle</option>
                        </select>
                        <br>
                        <label>Seat Number*</label>
                        <span class="windowseat">
                            <select name="seatNumber">
                                <c:forEach items="${flight.seatDirectory}" var="seat">
                                    <c:if test="${(seat.seatType=='WINDOW')&&(seat.booked=='NO')}">
                                        <option value="${seat.seatNumber}">${seat.seatNumber}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </span>
                        <span class="aisleseat">
                            <select name="seatNumber">
                                <c:forEach items="${flight.seatDirectory}" var="seat">
                                    <c:if test="${(seat.seatType=='AISLE')&&(seat.booked=='NO')}">
                                        <option value="${seat.seatNumber}">${seat.seatNumber}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </span>
                        <span class="middleseat">
                            <select name="seatNumber">
                                <c:forEach items="${flight.seatDirectory}" var="seat">
                                    <c:if test="${(seat.seatType=='MIDDLE')&&(seat.booked=='NO')}">
                                        <option value="${seat.seatNumber}">${seat.seatNumber}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </span>
                        <input name="id" value="${flight.flightId}" type="hidden">
                        <input name="action" value="bookTicket" type="hidden">
                        <p id="submission"><input type="submit" value="Book Ticket"></p>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/validate-booking.js"></script>
</html>
