<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout wide="true">
    <div class="bg-light py-3">
        <div class="container">
            <div class="row no-gutters">

                <div class="col-md-3">
                    <img src="${person.photo}" width="300" class="img-thumbnail" style="overflow: hidden;" />
                </div>

                <div class="col pl-2">
                    <h1 class="text-success">${person.fullName()}</h1>
                    <h2 class="text-muted h5">${person.profession}</h2>
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4">
                        <c:forEach var="feature" items="${person.features}">
                            <div class="col pt-3">
                                <b class="text-success"><spring:message code="${feature.key}" /></b> <br /> ${feature.value}
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="container my-3">${person.description}</div>

    <div class="container">
        <div class="row">

            <div class="col">

                <h3>${person.comments.size()} Comment<c:if test="${person.comments.size() > 1}">s</c:if></h3>

                <sec:authorize access="isAuthenticated()">
                    <hr />

                    <spring:message 
                        code="site.bio.message.${message}" 
                        arguments="${arguments}" 
                        var="lMessage" />
                    <t:message 
                        show="${message != null}" 
                        type="success" 
                        message="${lMessage}" />

                    <form:form modelAttribute="commentDTO">
                        <div class="form-group">
                            <form:textarea path="content" cssClass="form-control" />
                            <form:errors cssClass="text-danger" path="content" />
                        </div>
                        <div class="form-group">
                            <form:button class="btn btn-primary"><spring:message code="site.bio.comment.button" /></form:button>
                            </div>
                    </form:form>
                </sec:authorize>

                <hr />

                <c:forEach var="comment" items="${person.comments}">
                    <div class="card my-3">
                        <div class="card-body">
                            <h5 class="card-title">${comment.author.fullName()}</h5>
                            <fmt:parseDate value="${person.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdDate" />
                            <h6 class="card-subtitle mb-2 text-muted"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${createdDate}" /></h6>
                            <p class="card-text">${comment.content}</p>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>
    </div>

</t:layout>
