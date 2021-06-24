<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Пользователи</title>
</head>
    <body>
    <h2>Список пользователей</h2>
    <h3>
        <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
    </h3>
    <h4>
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </h4>
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
            <th colspan="2">Роль</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.middleName}</td>
                <td>${user.academicDegree.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.cathedra==null}"> - </c:when>
                    <c:otherwise>
                        ${user.cathedra.name}
                    </c:otherwise>
                    </c:choose>
                </td>
                <td>${user.email}</td>
                <td>
                    <c:forEach var="role" items="${user.roles}">
                        ${role.name}
                    </c:forEach>
                </td>
                <td>
                    <c:if test="${!user.roles.contains(roleAdmin)}">
                        <form action="${pageContext.request.contextPath}/user/all" method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit">Удалить</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <a href="${pageContext.request.contextPath}/">Главная</a>
    </body>
</html>