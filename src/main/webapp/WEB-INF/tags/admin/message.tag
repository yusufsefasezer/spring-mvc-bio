<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="message" pageEncoding="UTF-8" %>

<%@attribute name="message" %>
<%@attribute name="type" %>
<%@attribute name="show" required="true" type="Boolean" %>

<c:if test="${show}">
    <div class="alert alert-${type}" role="alert">
        <c:out value="${message}" escapeXml="true"/>
    </div>
</c:if>