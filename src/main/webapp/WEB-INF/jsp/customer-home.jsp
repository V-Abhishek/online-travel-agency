<%-- 
    Document   : customer-home
    Created on : Apr 15, 2020, 1:54:09 PM
    Author     : Abhishek
--%>

<jsp:include page="/content/header.jsp" />
<div class='madhya'>
    <form action="customer.htm" method="post">
        <input type="hidden" name="action" value="searchFlight">
        <input type="submit" value="TRAVEL RESERVATIONS">
    </form>
    <form action="customer.htm" method="post">
        <input type="hidden" name="action" value="viewTickets">
        <input type="submit" value="VIEW MY TICKETS">
    </form>
    <form action="customer.htm" method="post">
        <input type="hidden" name="action" value="changeProfile">
        <input type="submit" value="UPDATE PROFILE">
    </form>
</div>
<jsp:include page="/content/footer.jsp" />