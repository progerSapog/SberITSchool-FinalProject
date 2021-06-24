<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
  <sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
  </sec:authorize>

  <h2>Вход в систему</h2>
  <div>
    <form method="POST" action="${pageContext.request.contextPath}/login">
      <label>
        <input name="email" type="text" placeholder="email"/>
      </label>
      <label>
        <input name="password" type="password" placeholder="password"/>
      </label>
      <button type="submit">Войти</button>
      <h4><a href="${pageContext.request.contextPath}/registration">Зарегистрироваться</a></h4>
    </form>
  </div>

</body>
</html>
