<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <form:form modelAttribute="memberDTO">
        <div class="container">
            <div class="row">
                <div class="col">

                    <div class="form-group">
                        <spring:message code="site.admin.page.members.firstname" var="firstname" />
                        <form:input cssClass="form-control form-control-lg" placeholder="${firstname}" path="firstName" />
                        <form:errors cssClass="text-danger" path="firstName" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.members.lastname" var="lastname" />
                        <form:input cssClass="form-control form-control-lg" placeholder="${lastname}" path="lastName" />
                        <form:errors cssClass="text-danger" path="lastName" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.members.email" var="email" />
                        <form:input disabled="true" type="email" cssClass="form-control-plaintext form-control-lg" placeholder="${email}" path="email" />
                    </div>

                    <div class="form-group">
                        <form:select cssClass="form-control form-control-lg" path="role" >
                            <form:options />
                        </form:select>
                        <form:errors cssClass="text-danger" path="role" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.members.description" var="description" />
                        <form:textarea rows="5" cssClass="form-control" placeholder="${description}" path="description"/>
                        <form:errors cssClass="text-danger" path="description" />
                    </div>

                    <div class="form-group">
                        <form:button class="btn btn-secondary btn-lg btn-block">
                            <spring:message code="site.admin.page.members.update" />
                        </form:button>
                    </div>

                </div>
            </div>
        </div>
    </form:form>

</t:layout>
