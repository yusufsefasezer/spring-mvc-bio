<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.bio.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="success" message="${lMessage}" />

    <!--table-tool-->
    <div class="row mb-3">
        <div class="col">
            <form class="form-inline">
                <select class="form-control" id="export" onchange="selectedIndex > 0 ? location = 'bio-' + value : ''">
                    <option selected="selected"><spring:message code="site.admin.page.bio.export" /></option>
                    <option value="pdf">PDF</option>
                    <option value="excel">Excel</option>
                </select>
            </form>
        </div>

        <div class="col-md-auto my-2 my-xs-0">
            <a href="bio-add" class="btn btn-success d-block d-sm-inline">+ <spring:message code="site.admin.page.bio.add" /></a>
        </div>
    </div>

    <c:choose>
        <c:when test="${!people.hasContent()}">
            <spring:message code="site.page.norecord" />
        </c:when>

        <c:otherwise>
            <!--table-->
            <table class="table">
                <thead class="text-uppercase text-black-50">
                    <tr>
                        <th><spring:message code="site.admin.page.bio.name" /></th>
                        <th><spring:message code="site.admin.page.bio.profession" /></th>
                        <th><spring:message code="site.admin.page.bio.date" /></th>
                        <th><spring:message code="site.admin.page.bio.status" /></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="person" items="${people.content}">
                        <tr>
                            <td>${person.fullName()}</td>
                            <td>${person.profession}</td>

                            <fmt:parseDate value="${person.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdDate" />
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${createdDate}" /></td>

                            <c:set var="statusClass" value="text-success" />
                            <c:choose>
                                <c:when test="${person.status == 'DRAFT'}">
                                    <c:set var="statusClass" value="text-warning" />
                                </c:when>
                                <c:when test="${person.status == 'PRIVATE'}">
                                    <c:set var="statusClass" value="text-danger" />
                                </c:when>
                            </c:choose>
                            <td class="${statusClass} text-capitalize">${fn:toLowerCase(person.status)}</td>

                            <td>
                                <a href="bio-edit/${person.id}" class="text-dark mr-3">
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                                    </svg>
                                </a>
                                <a href="bio-delete/${person.id}" class="text-danger">
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
        </c:otherwise>
    </c:choose>



    <c:if test="${people.getTotalPages() > 1}">
        <!--pagination-->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${people.hasPrevious()}">
                    <li class="page-item">
                        <a class="page-link" href="bio?page=${people.number - 1}<c:if test="${param.term != null}">&term=${param.term}</c:if>"><spring:message code="site.admin.pagination.previous" /></a>
                        </li>
                </c:if>
                <c:forEach var="i" begin="0" end="${people.getTotalPages() - 1}">
                    <li class="page-item <c:if test="${people.number == i}">active</c:if>">
                        <a class="page-link" href="bio?page=${i}<c:if test="${param.term != null}">&term=${param.term}</c:if>">${i + 1}</a>
                        </li>
                </c:forEach>
                <c:if test="${people.hasNext()}">
                    <li class="page-item">
                        <a class="page-link" href="bio?page=${people.number + 1}<c:if test="${param.term != null}">&term=${param.term}</c:if>"><spring:message code="site.admin.pagination.next" /></a>
                        </li>
                </c:if>
            </ul>
        </nav>
    </c:if>

</t:layout>
