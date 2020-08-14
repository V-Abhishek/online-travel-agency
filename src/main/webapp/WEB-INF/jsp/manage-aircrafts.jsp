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

        #submission {
            display: block;
            position: relative;
            left: 50%;
            top: 10px;
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
        <title>Aircrafts</title>
    </head>
    <body>
        <h1 align="center">CURRENT FLEET</h1>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">Empty Fleet</h2>
            </c:when>
            <c:otherwise>
                <table id="customers">
                    <thead>
                        <tr>
                            <th>Serial Number</th>
                            <th>Make</th>
                            <th>Model</th>
                            <th>Entertainment Available</th>
                            <th>WiFi Available</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestList}" var = "aircraft">
                            <tr>
                                <td><c:out value="${aircraft.serialNo}"/></td>
                                <td><c:out value="${aircraft.name}"/></td>
                                <td><c:out value="${aircraft.model}"/></td>
                                <td><c:out value="${aircraft.hasEntertainment}"/></td>
                                <td><c:out value="${aircraft.hasWifi}"/></td>
                                <td><a href="flight.htm?action=manageFlights&id=${aircraft.aircraftId}">Manage Flights </a><a href="airliner.htm?action=update&id=${aircraft.aircraftId}">Update Details </a><a href="airliner.htm?action=delete&id=${aircraft.aircraftId}">Delete Aircraft</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>      
        </c:otherwise>
    </c:choose>
</body>
</html>

