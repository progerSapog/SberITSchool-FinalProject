<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавление кафедры</title>
</head>
    <h2>Добавление кафедры</h2>

    <body>
    <h3>
        <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
    </h3>
    <h4>
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </h4>

    <div>
    <form:form method="post" action="save" modelAttribute="cathedraForAdd">
        <form:hidden path="id"/>
        <div>
            Название кафедры:
            <form:input type="text" path="name" placeholder="Название кафедры" autofocus="true"/>
            <form:errors path="name"/>
        </div>
        <input type="submit" value="OK">
    </form:form>

        <a href="${pageContext.request.contextPath}/">Главная</a>
</body>
</html>
