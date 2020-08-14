<%-- 
    Document   : approve-accounts
    Created on : Apr 11, 2020, 2:59:55 PM
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
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts Approval</title>
    </head>
    <body>
        <h1 align="center">Airliner Account Approval Requests</h1>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">No Pending Requests</h2>
            </c:when>
            <c:otherwise>
                <form action="traveladmin.htm" method="post">
                    <table id="customers">
                        <thead>
                            <tr>
                                <th>Airliner</th>
                                <th>Contact Person Name</th>
                                <th>Cellphone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Approve/Reject</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestList}" var = "airliner">
                                <tr>
                                    <td><c:out value="${airliner.airliner}"/></td>
                                    <td><c:out value="${airliner.contactName}"/></td>
                                    <td><c:out value="${airliner.cellphone}"/></td>
                                    <td><c:out value="${airliner.email}"/></td>
                                    <td><c:out value="${airliner.street}"/>, <c:out value="${airliner.city}"/> , <c:out value="${airliner.state}"/>, <c:out value="${airliner.zipCode}"/>, <c:out value="${airliner.country}"/></td>
                                    <td><input type="radio" name="${airliner.id}" value="approve" required/>Approve  <input type="radio" name="${airliner.id}" value="reject" required/>Reject</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="hidden" name="action" value="ApAndDisapList">
                    <input type="hidden" name="approvalList" value="">
                    <input type="hidden" name="rejectlList" value="">
                    <p id="submission"><input type="submit"></p>
                </form>      
            </c:otherwise>
        </c:choose>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $('input[type=submit]').on('click', function () {
            var aList = [];
            var rList = [];
            $("input[type=radio]:checked").each(function () {
                if (this.value == "approve" && this.checked == true)
                {
                    aList.push($(this).attr('name'));
                } else {
                    rList.push($(this).attr('name'));
                }
            });
            $("input[name='approvalList']").val(aList);
            $("input[name='rejectlList']").val(rList);
        });
    </script>
</html>
