<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <h3>
            <a href="${pageContext.request.contextPath}/user/update">${pageContext.request.userPrincipal.name}</a>
        </h3>
        <h4>
            <a href="${pageContext.request.contextPath}/logout">Выйти</a>
        </h4>
    </sec:authorize>

  <h4><a href="${pageContext.request.contextPath}/applications/all">Заявки</a></h4>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="${pageContext.request.contextPath}/login">Войти</a></h4>
        <h4><a href="${pageContext.request.contextPath}/registration">Регистрация</a></h4>
    </sec:authorize>

  <sec:authorize access="hasRole('ADMIN')">
      <h4><a href="${pageContext.request.contextPath}/department/all">Список кафедр</a></h4>
      <h4><a href="${pageContext.request.contextPath}/user/all">Список пользователей</a></h4>
  </sec:authorize>
</body>
</html>