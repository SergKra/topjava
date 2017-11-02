<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>

    <h2>Edit meal</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form:form modelAttribute="meal" method="post" action="${pageContext.request.contextPath}/meals/edit">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit">Edit</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
