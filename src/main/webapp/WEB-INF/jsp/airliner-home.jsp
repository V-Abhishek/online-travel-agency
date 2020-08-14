<%-- 
    Document   : airliner-home
    Created on : Apr 12, 2020, 4:36:02 PM
    Author     : Abhishek
--%>

<jsp:include page="/content/header.jsp" />
<div class='madhya'>
    <form action="airliner.htm" method="post">
        <input type="hidden" name="action" value="addAircrafts">
        <input type="submit" value="ADD AIRCRAFTS">
    </form>
    <form action="airliner.htm" method="post">
        <input type="hidden" name="action" value="manageAircrafts">
        <input type="submit" value="MANAGE AIRCRAFTS">
    </form>
    <form action="airliner.htm" method="post">
        <input type="hidden" name="action" value="changeProfile">
        <input type="submit" value="UPDATE PROFILE">
    </form>
</div>
<jsp:include page="/content/footer.jsp" />