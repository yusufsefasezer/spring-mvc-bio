<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout>
    <h2><spring:message code="site.page.login" /></h2>

    <div class="row justify-content-center">
        <section class="col-md-6">
            <form:form modelAttribute="login">

                <t:message 
                    show="${param.error != null}" 
                    type="danger" 
                    message="${message}" />

                <!--email-->
                <div class="form-group">
                    <form:label path="email"><spring:message code="site.page.login.email" /></form:label>
                    <form:input type="email" cssClass="form-control" path="email" placeholder="yusufsezer@mail.com" required="required" autofocus="autofocus" />
                </div>

                <!--password-->
                <div class="form-group">
                    <form:label path="password"><spring:message code="site.page.login.password" /></form:label>
                    <spring:message code="site.page.login.password" var="password" />
                    <form:password cssClass="form-control" path="password" placeholder="${password}" required="required" />
                </div>

                <!--submit-->
                <form:button class="btn btn-primary btn-block"><spring:message code="site.page.login.button" /></form:button>

            </form:form>
        </section>
    </div>
</t:layout>
