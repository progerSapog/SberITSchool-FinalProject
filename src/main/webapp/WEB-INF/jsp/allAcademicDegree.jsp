<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <body>
    <h2>All AcademicDegree</h2>
    <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="academicDegree" items="${allAcademicDegree}">

            <tr>
                <td>${academicDegree.id}</td>
                <td>${academicDegree.name}</td>
            </tr>
        </c:forEach>

    </table>
    <br>
    </body>
</html>