<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@tag description="header" pageEncoding="UTF-8" %>
<header>

    <div class="container">

        <!--top-bar-->
        <div class="row p-1">
            <div class="col">
                <c:out value="${description}" escapeXml="true" />
            </div>
            <div class="col text-md-right">
                <sec:authorize access="isAnonymous()">
                    <a href="login" class="font-weight-bold"><spring:message code="site.header.login" /></a> 
                    | 
                    <a href="register" class="font-weight-bold"><spring:message code="site.header.register" /></a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.Username" /> 
                    <sec:authorize url="/admin">
                        |
                        <a href="admin" class="font-weight-bold"><spring:message code="site.header.admin" /></a>
                    </sec:authorize>
                    | 
                    <a href="logout" class="font-weight-bold"><spring:message code="site.header.logout" /></a>
                </sec:authorize>
            </div>
        </div>

        <!--logo-->
        <div class="row my-2 justify-content-between">
            <div class="col-md-8">
                <h1><a href="." class="text-success"><c:out value="${title}" escapeXml="true" /></a></h1>
            </div>
            <div class="col-md-4">
                <form action="search" class="form-inline input-group">
                    <input id="term" name="term" type="search" minlength="3" maxlength="20" required="required" class="form-control" placeholder="<spring:message code="site.header.search" />">
                    <div class="input-group-append">
                        <button class="btn btn-success btn-block" type="submit"><spring:message code="site.header.search" /></button>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <!--letter-bar-->
    <div style="background-color: #e3f2fd;">
        <div class="container">
            <nav class="navbar navbar-light text-uppercase font-weight-bold">
                <c:forEach var="i" begin="97" end="122">
                    <a href="letter-${Character.toString(i)}" class="text-dark">${Character.toString(i)}</a>
                </c:forEach>
            </nav>
        </div>
    </div>

</header>
