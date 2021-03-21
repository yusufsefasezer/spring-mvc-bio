<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag description="breadcrumb" pageEncoding="UTF-8" %>
<div class="row no-gutters text-black-50">
    <div class="col-md-10">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-light pl-0">
                <li class="breadcrumb-item"><span><spring:message code="site.admin.breadcrumb.prefix" /></span></li>
                <li class="breadcrumb-item"><a href="."><spring:message code="site.admin.breadcrumb.home" /></a></li>
                <li class="breadcrumb-item active" aria-current="page"><c:out value="${pageTitle}" escapeXml="true" /></li>
            </ol>
        </nav>
    </div>
    <div class="col">
        <form action="bio">
            <input id="term" name="term" type="search" minlength="3" maxlength="20" required="required" class="form-control" placeholder="<spring:message code="site.admin.header.search" />" />
        </form>
    </div>
</div>

<!--title-->
<h1 class="h2">
    <c:choose>
        <c:when test="${welcome != null}">
            ${welcome}
        </c:when>
        <c:otherwise>
            <c:out value="${pageTitle}" escapeXml="true" />
        </c:otherwise>
    </c:choose></h1>
<hr />
