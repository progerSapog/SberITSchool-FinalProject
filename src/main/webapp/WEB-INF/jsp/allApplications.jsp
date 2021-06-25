<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Заявки</title>
</head>
<body>
<h2>Список всех заявок</h2>
<sec:authorize access="isAuthenticated()">
    <h3>
        <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
    </h3>
    <h4>
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </h4>
</sec:authorize>
<br>
<table>
    <tr>
        <th>Номер аудитории</th>
        <th>Текст заявки</th>
        <th>Автор</th>
        <sec:authorize access="isAuthenticated()">
            <th>Действия</th>
        </sec:authorize>
    </tr>
    <c:forEach var="application" items="${allApplications}">
        <tr>
            <td>${application.audienceNumber}</td>
            <td>${application.text}</td>
            <td>${application.user.firstName} ${application.user.lastName} ${application.user.middleName}</td>

            <td>
                <sec:authorize access="isAuthenticated() and hasRole('ADMIN')">
                    <form action="${pageContext.request.contextPath}/applications/all" method="post">
                        <input type="hidden" name="applicationId" value="${application.id}">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit">Удалить</button>
                    </form>
                </sec:authorize>

                <c:if test="${application.user.id.equals(user.id)}">
                    <sec:authorize access="!hasRole('ADMIN')">
                        <form action="${pageContext.request.contextPath}/applications/all" method="post">
                            <input type="hidden" name="applicationId" value="${application.id}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit">Удалить</button>
                        </form>
                    </sec:authorize>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/applications/add">Добавить заявку</a>
</sec:authorize>

<br>
<a href="${pageContext.request.contextPath}/">Главная</a>
</body>
</html>