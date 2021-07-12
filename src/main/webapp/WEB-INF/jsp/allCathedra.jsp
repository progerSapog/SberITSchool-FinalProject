<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Кафедры</title>
</head>
    <body>
    <h2>Список кафедр</h2>
    <h3>
        <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
    </h3>
    <h4>
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </h4>
    <br>
        <table>
            <tr>
                <th>Название</th>
                <th colspan="2">Действия</th>
            </tr>
            <c:forEach var="department" items="${allCathedra}">

                <c:url var="updateButton" value="/department/update">
                    <c:param name="cathedraId" value="${department.id}"/>
                </c:url>

                <tr>
                    <td>${department.name}</td>
                    <td>
                        <input type="button" value="Изменить" onclick="window.location.href = '${updateButton}'"/>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/department/all" method="post">
                            <input type="hidden" name="cathedraId" value="${department.id}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit">Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <br>

    <input type="button" value="Добавить"  onclick="window.location.href = '${pageContext.request.contextPath}/department/add'"/>
    <a href="${pageContext.request.contextPath}/">Главная</a>
    </body>
</html>