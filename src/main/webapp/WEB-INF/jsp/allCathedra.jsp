<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <body>
    <h2>All Cathedra</h2>
    <br>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="cathedra" items="${allCathedra}">

                <tr>
                    <td>${cathedra.id}</td>
                    <td>${cathedra.name}</td>
                </tr>
            </c:forEach>

        </table>
    <br>
    </body>
</html>