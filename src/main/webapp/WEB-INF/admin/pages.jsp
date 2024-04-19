<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.pages.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="info" message="${lMessage}" />

    <!--table-tool-->
    <div class="row mb-3 justify-content-md-end">
        <div class="col-md-auto">
            <div class="form-inline">
                <div class="form-group">
                    <a href="page-add" class="btn btn-success">+ <spring:message code="site.admin.page.pages.add" /></a>
                </div>
            </div>
        </div>
    </div>

    <!--table-->
    <table class="table">

        <thead class="text-uppercase text-black-50">
            <tr>
                <th><spring:message code="site.admin.page.pages.title" /></th>
                <th><spring:message code="site.admin.page.pages.date" /></th>
                <th><spring:message code="site.admin.page.pages.status" /></th>
                <th></th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="page" items="${pageList}">
                <tr>
                    <td>${page.title}</td>

                    <fmt:parseDate value="${page.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdDate" />
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${createdDate}" /></td>

                    <c:set var="statusClass" value="success" />
                    <c:choose>
                        <c:when test="${page.status == 'DRAFT'}">
                            <c:set var="statusClass" value="warning" />
                        </c:when>
                        <c:when test="${page.status == 'PRIVATE'}">
                            <c:set var="statusClass" value="danger" />
                        </c:when>
                    </c:choose>
                    <td class="text-${statusClass} text-capitalize">${fn:toLowerCase(page.status)}</td>

                    <td>
                        <a href="page-edit/${page.id}" class="text-dark mr-3">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                            </svg>
                        </a>
                        <a href="page-delete/${page.id}" class="text-danger">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                            </svg>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>

    </table>

</t:layout>
