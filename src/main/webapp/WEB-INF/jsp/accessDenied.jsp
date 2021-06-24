<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Ошибка доступа</title>
</head>
<body>
  <h2>Отказано в доступе.</h2>
  Вернутся на <a href="${pageContext.request.contextPath}/">главную</a> страницу.
</body>
</html>
