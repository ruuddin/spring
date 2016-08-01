<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course List</title>
</head>
<body>
<table>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Credits</th>
    <th></th>
  </tr>
  <c:forEach items="${courses}" var="course">
    <tr>
      <td>${course.id}</td>
      <td>${course.name}</td>
      <td>${course.credits}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>