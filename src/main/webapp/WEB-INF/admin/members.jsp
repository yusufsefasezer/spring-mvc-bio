<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <spring:message code="site.admin.page.members.message.${message}" var="lMessage" />
    <t:message show="${message != null}" type="success" message="${lMessage}" />

    <!--table-->
    <table class="table">
        <thead class="text-uppercase text-black-50">
            <tr>
                <th><spring:message code="site.admin.page.members.name" /></th>
                <th><spring:message code="site.admin.page.members.email" /></th>
                <th><spring:message code="site.admin.page.members.role" /></th>
                <th><spring:message code="site.admin.page.members.birthdate" /></th>
                <th><spring:message code="site.admin.page.members.date" /></th>
                <th></th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="member" items="${memberList}">
                <tr>
                    <td>${member.fullName()}</td>
                    <td>${member.email}</td>

                    <c:set var="badgeClass" value="primary" />
                    <c:choose>
                        <c:when test="${member.role == 'ADMINISTRATOR'}">
                            <c:set var="badgeClass" value="danger" />
                        </c:when>
                        <c:when test="${member.role == 'EDITOR'}">
                            <c:set var="badgeClass" value="warning" />
                        </c:when>
                    </c:choose>
                    <td><span class="badge badge-${badgeClass} p-2 text-capitalize">${fn:toLowerCase(member.role)}</span></td>

                    <fmt:parseDate value="${member.birthDate}" pattern="yyyy-MM-dd" var="birthDate" />
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${birthDate}" /></td>

                    <fmt:parseDate value="${member.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdDate" />
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${createdDate}" /></td>

                    <td>
                        <c:if test="${!member.active}">
                            <a href="member-approve/${member.id}" class="text-success">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z" />
                                </svg>
                            </a>
                        </c:if>
                        <a href="member-edit/${member.id}" class="text-dark mx-3">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                            </svg>
                        </a>
                        <a href="member-delete/${member.id}" class="text-danger">
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
