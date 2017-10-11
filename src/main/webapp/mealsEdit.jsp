<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>

<h2 align="center">Meal</h2>

<form method="post" action="meals" name="frmAddUser">
    ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${meal.id}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    Date : <input
        type="text" name="date"
        value="${meal.dateTime.format(local)}" /> <br />

    <input
            type="submit" value="Submit" />
</form>


</body>
</html>
