<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <body>
    <h2>All Users</h2>
    <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Отчество</th>
            <th>Ученая степень</th>
            <th>Кафедра</th>
            <th>Почта</th>
            <th>Роль</th>
        </tr>
        <c:forEach var="user" items="${allUsers}">

            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.middleName}</td>
                <td>${user.academicDegree.name}</td>
                <td>${user.cathedra.name}</td>
                <td>${user.email}</td>
                <td>
                    <c:forEach var="role" items="${user.roles}">
                        ${role.name}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br>
    </body>
</html>