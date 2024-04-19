<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h1>Error!</h1>
        <c:forEach items="${exception.stackTrace}" var="trace">
            <c:out value="${trace}" />
        </c:forEach>
    </body>
</html>
