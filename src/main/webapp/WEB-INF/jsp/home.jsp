<%-- 
    Document   : home
    Created on : Apr 3, 2020, 9:43:49 PM
    Author     : Abhishek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/current_date" prefix="dateformat" %>
<jsp:include page="/content/header.jsp" />
<div class="home-search">
    <form action="search.htm" method="post" onsubmit="return validateForm();">
        <label>Flying from*</label>
        <input name="departureLocation" placeholder="Enter departure location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only">
        <label>Going to*</label>
        <input name="arrivalLocation" placeholder="Enter arrival location" required="" type="text" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" title="Alphabets only">
        <label>Date of Departure*</label>
        <input type="date" name="date" min="<dateformat:CurrentDateFormat format='yyyy-MM-dd'/>" required>
        <span class="validity"></span>
        <input name="comingFrom" value="home" type="hidden">
        <input type="submit" value="Find Flights">
    </form>
</div>
<jsp:include page="/content/footer.jsp" />
