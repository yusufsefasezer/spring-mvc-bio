<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.comments.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="info" message="${lMessage}" />

    <c:forEach var="comment" items="${commentList}">
        
        <!--comments-->
        <form:form modelAttribute="comment">
            <div class="card mb-4">

                <div class="card-header">
                    <strong>${comment.author.fullName()} - ${comment.id}</strong>
                </div>

                <div class="card-body card-block">
                    <div class="form-group">
                        <input type="hidden" name="id" value="${comment.id}" />
                        <textarea class="form-control" name="content">${comment.content}</textarea>
                    </div>
                </div>

                <div class="card-footer">
                    <button type="submit" class="btn btn-primary btn-sm"><spring:message code="site.admin.page.comments.update" /></button>
                    <c:choose>
                        <c:when test="${comment.active}">
                            <a href="comment-disapprove/${comment.id}" class="btn btn-warning btn-sm"><spring:message code="site.admin.page.comments.disapprove" /></a>
                        </c:when>
                        <c:otherwise>
                            <a href="comment-approve/${comment.id}" class="btn btn-success btn-sm"><spring:message code="site.admin.page.comments.approve" /></a>
                        </c:otherwise>
                    </c:choose>
                    <a href="comment-delete/${comment.id}" class="btn btn-danger btn-sm"><spring:message code="site.admin.page.comments.delete" /></a>
                </div>

            </div>
        </form:form>
    </c:forEach>

</t:layout>
