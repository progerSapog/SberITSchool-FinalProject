<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<h2>All Applications</h2>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>Audience Number</th>
        <th>Text</th>
        <th>User</th>
    </tr>
    <c:forEach var="application" items="${allApplications}">

        <tr>
            <td>${application.id}</td>
            <td>${application.audienceNumber}</td>
            <td>${application.text}</td>
            <td>${application.user.firstName} ${application.user.lastName} ${application.user.middleName}</td>
        </tr>
    </c:forEach>

</table>
<br>
</body>
</html>