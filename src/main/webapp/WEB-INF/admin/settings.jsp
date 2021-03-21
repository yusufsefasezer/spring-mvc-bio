<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin" %>
<t:layout>

    <spring:message code="site.admin.page.settings.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="success" message="${lMessage}" />
    
    <form:form cssClass="form-horizontal" modelAttribute="settingList">

        <c:forEach var="setting" varStatus="status" items="${settingList.settings}">
            <div class="row form-group">
                <div class="col col-md-3">
                    <form:label path="settings[${status.index}].SValue"><spring:message code="site.admin.page.settings.${setting.SKey}" /></form:label>
                </div>
                <div class="col-12 col-md-9">
                    <form:hidden cssClass="form-control" path="settings[${status.index}].SKey" />
                    <form:input cssClass="form-control" path="settings[${status.index}].SValue" />
                </div>
            </div>
        </c:forEach>

        <div class="form-group">
            <form:button class="btn btn-primary"><spring:message code="site.admin.page.settings.save" /></form:button>
        </div>
    </form:form>

</t:layout>
