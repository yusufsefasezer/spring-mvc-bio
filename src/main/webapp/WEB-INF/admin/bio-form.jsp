<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.bio.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="success" message="${lMessage}" />

    <form:form modelAttribute="bioDTO">
        <div class="container">
            <div class="row">

                <div class="col-md-2">
                    <div class="image">
                        <c:choose>
                            <c:when test="${not empty bioDTO.photo}">
                                <img src="${bioDTO.photo}" width="150" />
                            </c:when>
                            <c:otherwise>
                                <svg xmlns="http://www.w3.org/2000/svg" height="150" width="150" version="1.1" viewBox="-300 -300 600 600" font-size="72" text-anchor="middle">
                                    <circle stroke="#AAA" stroke-width="10" r="280" fill="#FFF" />
                                    <text style="fill:#444;">
                                        <tspan x="0" y="-8"><spring:message code="site.admin.page.bio.image1" /></tspan>
                                        <tspan x="0" y="80"><spring:message code="site.admin.page.bio.image2" /></tspan>
                                    </text>
                                </svg>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <input class="d-none" type="file" name="image" id="image" />
                    <form:hidden path="photo" />
                    <form:errors cssClass="text-danger" path="photo" />
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <spring:message code="site.admin.page.bio.firstname" var="firstname" />
                        <form:input cssClass="form-control" placeholder="${firstname}" path="firstName" />
                        <form:errors cssClass="text-danger" path="firstName" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.bio.lastname" var="lastname" />
                        <form:input cssClass="form-control" placeholder="${lastname}" path="lastName" />
                        <form:errors cssClass="text-danger" path="lastName" />
                    </div>

                    <div class="form-group">
                        <spring:message code="site.admin.page.bio.profession" var="profession" />
                        <form:input cssClass="form-control" placeholder="${profession}" path="profession" />
                        <form:errors cssClass="text-danger" path="profession" />
                    </div>

                    <div class="form-group">
                        <form:select cssClass="form-control" path="status">
                            <form:options />
                        </form:select>
                    </div>
                </div>

                <div class="col mb-4">
                    <div class="form-group">
                        <div id="features">
                            <c:forEach var="item" items="${bioDTO.features}">
                                <form:hidden path="features[${item.key}]" />
                            </c:forEach>
                        </div>
                        <select id="featureSelect" class="form-control">
                            <option><spring:message code="site.admin.page.bio.pleaseselect" /></option>
                            <c:forEach var="feature" items="${bioDTO.features}">
                                <option><c:out value="${feature.key} - ${feature.value}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" id="key" placeholder="<spring:message code="site.admin.page.bio.key" />" class="form-control" />
                    </div>
                    <div class="form-group">
                        <input type="text" id="value" placeholder="<spring:message code="site.admin.page.bio.value" />" class="form-control" />
                    </div>
                    <button id="btnAdd" class="btn btn-primary btn-block"><spring:message code="site.admin.page.bio.add" /></button>
                    <button id="btnUpdate" class="btn btn-success d-none"><spring:message code="site.admin.page.bio.update" /></button>
                    <button id="btnRemove" class="btn btn-danger d-none"><spring:message code="site.admin.page.bio.remove" /></button>
                </div>

            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <spring:message code="site.admin.page.bio.content" var="description" />
                        <form:textarea rows="9" cssClass="form-control" placeholder="${description}" path="description" />
                        <form:errors cssClass="text-danger" path="description" />
                    </div>
                    <div class="form-group">
                        <form:button class="btn btn-${action eq 'add' ? 'success': 'secondary' } btn-lg btn-block">
                            <spring:message code="site.admin.page.bio.${action eq 'add' ? 'save': 'update' }" />
                        </form:button>
                    </div>
                </div>
            </div>
        </div>
    </form:form>

</t:layout>
