<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout>
    <h2><spring:message code="site.page.register" /></h2>

    <div class="row justify-content-center">
        <section class="col-md-6">
            <form:form modelAttribute="registerDTO">

                <spring:message 
                    code="site.page.register.message.${message}" 
                    arguments="${arguments}" 
                    var="lMessage" />
                <t:message 
                    show="${message != null}" 
                    type="danger" 
                    message="${lMessage}" />

                <!--email-->
                <div class="form-group">
                    <spring:message code="site.page.register.email" var="email" />
                    <form:label path="email">${email}</form:label>
                    <form:input type="email" cssClass="form-control" placeholder="yusufsezer@mail.com" path="email" required="required" autofocus="autofocus" />
                    <form:errors cssClass="text-danger" path="email" />
                </div>

                <!--password-->
                <div class="form-group">
                    <spring:message code="site.page.register.password" var="password" />
                    <form:label path="email">${password}</form:label>
                    <form:password cssClass="form-control" placeholder="${password}" path="password" required="required" />
                    <form:errors cssClass="text-danger" path="password" />
                </div>

                <!--first name-->
                <div class="form-group">
                    <spring:message code="site.page.register.firstname" var="firstname" />
                    <form:label path="firstName">${firstname}</form:label>
                    <form:input cssClass="form-control" placeholder="${firstname}" path="firstName" />
                    <form:errors cssClass="text-danger" path="firstName" />
                </div>

                <!--last name-->
                <div class="form-group">
                    <spring:message code="site.page.register.lastname" var="lastname" />
                    <form:label path="lastName">${lastname}</form:label>
                    <form:input cssClass="form-control" placeholder="${lastname}" path="lastName" />
                    <form:errors cssClass="text-danger" path="lastName" />
                </div>

                <!--Birth date-->
                <div class="form-group">
                    <spring:message code="site.page.register.birthdate" var="birthdate" />
                    <fmt:parseDate value="${author.birthDate}" pattern="yyyy-MM-dd" var="pBirthDate" />
                    <fmt:formatDate value="${pBirthDate}" var="fBirthDate" pattern="yyyy-MM-dd" />
                    <form:label path="birthDate">${birthdate}</form:label>
                    <form:input type="date" cssClass="form-control" placeholder="${birthdate}" path="birthDate" value="${fBirthDate}" />
                    <form:errors cssClass="text-danger" path="birthDate" />
                </div>

                <!--Description-->
                <div class="form-group">
                    <spring:message code="site.page.register.description" var="description" />
                    <form:label path="description">${description}</form:label>
                    <form:textarea cssClass="form-control" placeholder="${description}" path="description" />
                    <form:errors cssClass="text-danger" path="description" />
                </div>

                <!--submit-->
                <form:button class="btn btn-primary btn-block"><spring:message code="site.page.register.button" /></form:button>

            </form:form>
        </section>
    </div>

</t:layout>
