<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
 <div>
  <form:form method="post" modelAttribute="userForm">
    <h2>Регистрация</h2>
    <div>
      <form:input type="text" path="user.firstName" placeholder="Имя" autofocus="true"></form:input>
      <form:errors path="user.firstName"> </form:errors>
<%--      ${firstNameError}--%>
    </div>

    <div>
      <form:input type="text" path="user.lastName" placeholder="Фамилия" autofocus="true"></form:input>
      <form:errors path="user.lastName"> </form:errors>
<%--      ${lastNameError}--%>
    </div>

    <div>
      <form:input type="text" path="user.middleName" placeholder="Отчество" autofocus="true"></form:input>
      <form:errors path="user.middleName"> </form:errors>
<%--      ${middleNameError}--%>
    </div>

    <div>
      <form:input type="text" path="user.email" placeholder="e-mail" autofocus="true"></form:input>
      <form:errors path="user.email"> </form:errors>
<%--      ${emailError}--%>
    </div>

    <div>
      <p>Выберите кафедру: </p>
      <form:select path="cathedraList">
        <c:forEach var="carhedra" items="${userForm.cathedraList}">
          <option value=">${carhedra}">${carhedra.name}</option>
        </c:forEach>
      </form:select>
    </div>


    <div>
      <p>Выберите ученую степень: </p>
      <form:select path="academicDegreeList">
        <c:forEach var="academicDegree" items="${userForm.academicDegreeList}">
          <option value=">${academicDegree}">${academicDegree.name}</option>
        </c:forEach>
      </form:select>
    </div>

    <div>
      <form:input type="password" path="user.password" placeholder="Password"></form:input>
    </div>

    <div>
      <form:input type="password" path="user.passwordConfirm" placeholder="Confirm your password"></form:input>
      <form:errors path="user.password"> </form:errors>
<%--      ${passwordError}--%>
    </div>

    <button type="submit">Зарегистрироваться</button>
  </form:form>

  <a href="${pageContext.request.contextPath}/allApplications">Главная</a>
 </div>

</body>
</html>
