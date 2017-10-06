
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Meals List</title>
</head>
<body>

<h2 align="center">Meals List</h2>
<table border="1">
    <tr>
        <th width="150">Time</th>
        <th width="100">Description</th>
        <th width="100">Calories</th>

    </tr>
<c:forEach items="${mealList}" var="meal">

    <c:if test="${meal.exceed==true}">
    <tr>
        <td><font color="red"> ${meal.dateTime.format(local)}</font></td>
        <td><font color="red">${meal.description}</font></td>
        <td><font color="red">${meal.calories}</font></td>

        </c:if>

    </tr>
    <c:if test="${meal.exceed==false}">
        <tr>
        <td><font color="green"> ${meal.dateTime.format(local)}</font></td>
        <td><font color="green">${meal.description}</font></td>
        <td><font color="green">${meal.calories}</font></td>
    </c:if>

    </tr>

</c:forEach>
    </table>

</body>
</html>
