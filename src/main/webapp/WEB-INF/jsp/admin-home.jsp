<%-- 
    Document   : admin-home
    Created on : Apr 11, 2020, 2:59:40 AM
    Author     : Abhishek
--%>
<jsp:include page="/content/header.jsp" />
<div class='madhya'>
    <form action="traveladmin.htm" method="post">
        <input type="hidden" name="action" value="requestList">
        <input type="submit" value="Accounts Approval Requests">
    </form>
    <form action="traveladmin.htm" method="post">
        <input type="hidden" name="action" value="existingAirliners">
        <input type="submit" value="Manage Airliners">
    </form>
    <form action="traveladmin.htm" method="post">
        <input type="hidden" name="action" value="viewBookings">
        <input type="submit" value="View Bookings">
    </form>
</div>
<jsp:include page="/content/footer.jsp" />
