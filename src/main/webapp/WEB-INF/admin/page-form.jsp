<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.pages.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="info" message="${lMessage}" />

    <form:form modelAttribute="pageDTO">
        <div class="container">
            <div class="row">
                <div class="col">

                    <div class="form-group">
                        <spring:message code="site.admin.page.pages.title" var="title" />
                        <form:input cssClass="form-control form-control-lg" placeholder="${title}" path="title" />
                        <form:errors cssClass="text-danger" path="title" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.pages.shortdescription" var="excerpt" />
                        <form:textarea rows="2" cssClass="form-control" placeholder="${excerpt}" path="excerpt" />
                    </div>

                    <div class="form-group">
                        <form:select cssClass="form-control form-control-lg" path="status">
                            <form:options />
                        </form:select>
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.pages.content" var="description" />
                        <form:textarea rows="9" cssClass="form-control" placeholder="${description}" path="description" />
                    </div>

                    <div class="form-group">
                        <form:button class="btn btn-${action eq 'add' ? 'primary': 'secondary' } btn-lg btn-block">
                            <spring:message code="site.admin.page.pages.${action eq 'add' ? 'save': 'update' }" />
                        </form:button>
                        </div>

                    </div>
                </div>
            </div>
    </form:form>

</t:layout>
