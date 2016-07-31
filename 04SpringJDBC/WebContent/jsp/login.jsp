<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${user.messageExists()}">
  <span style="color:red;">
    ${user.message}<br>
  </span>
</c:if>

<form id="loginForm" method="POST">
  User Id : <input type="text" name="userName" required="required" value="${user.userName}"><br>
  Password : <input type="password" name="password"><br>
  <button type="submit">Submit</button>
</form>
</body>
</html>