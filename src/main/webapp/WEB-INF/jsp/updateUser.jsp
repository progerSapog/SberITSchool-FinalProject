<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Профиль</title>
</head>
<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <form:hidden path="id"/>

        <h2>Данные профиля: </h2>
        <div>
            <form:input type="text" path="firstName" placeholder="Имя" autofocus="true"/>
            <form:errors path="firstName"/>
        </div>

        <div>
            <form:input type="text" path="lastName" placeholder="Фамилия" autofocus="true"/>
            <form:errors path="lastName"/>
        </div>

        <div>
            <form:input type="text" path="middleName" placeholder="Отчество" autofocus="true"/>
            <form:errors path="middleName"/>
        </div>

        <div>
            <form:input type="text" path="email" placeholder="e-mail" autofocus="true"/>
            <form:errors path="email"/>
            ${emailChangeError}
        </div>

        <div>
            <p>Выберите кафедру: </p>
            <form:select path="department">
                <c:forEach var="department" items="${cathedraList}">
                    <form:option value="${department}" label="${department.name}"/>
                </c:forEach>
            </form:select>
            <br>
        </div>

        <div>
            <p>Выберите ученую степень: </p>
            <form:select path="academicDegree">
                <c:forEach var="academicDegree" items="${academicDegreeList}">
                    <form:option value="${academicDegree}" label="${academicDegree.name}"/>
                </c:forEach>
            </form:select>
        </div>

        <br>
        <div>
            <form:input type="password" path="password" placeholder="Old password"/>
            <form:errors path="password"/>
            ${oldPasswordError}
        </div>

        <div>
            <form:input type="password" path="passwordToChange" placeholder="New password"/>
        </div>

        <div>
            <form:input type="password" path="passwordConfirm" placeholder="New password confirm"/>
            ${passwordError}
        </div>

        <button type="submit">Изменить</button>
    </form:form>
</div>
<a href="${pageContext.request.contextPath}/">Главная</a>
</body>
</html>