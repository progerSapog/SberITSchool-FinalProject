<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавление заявки</title>
</head>
<body>
    <h2>Составление заявки</h2>
    <h3>
        <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
    </h3>
    <h4>
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </h4>

    <div>
    <form:form method="POST" modelAttribute="appForm">
        <div>
            <div>
                Номер аудитории:
                <form:input type="text" path="audienceNumber" placeholder="Номер аудитории" autofocus="true"/>
                <form:errors path="audienceNumber"/>
            </div>
            <div>
                Текст заявки:
                <form:textarea type="text" path="text" placeholder="Текст заявки" autofocus="true"/>
                <form:errors path="text"/>
            </div>
        </div>
        ${appError}
        <button type="submit">Отправить</button>
    </form:form>

        <a href="${pageContext.request.contextPath}/">Главная</a>
</body>
</html>
