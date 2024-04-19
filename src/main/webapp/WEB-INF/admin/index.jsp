<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin/" %>
<t:layout>

    <!--statistic-->
    <div class="row">

        <div class="col-md-3">
            <div class="p-5" style="background-color: #00b26f;">
                <h2 class="h1 text-white font-weight-normal">${biographyCount}</h2>
                <h3 class="h5 text-uppercase text-white-50 font-weight-normal"><spring:message code="site.admin.page.home.biography" /></h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="p-5" style="background-color: #ff8300;">
                <h2 class="h1 text-white font-weight-normal">${pageCount}</h2>
                <h3 class="h5 text-uppercase text-white-50 font-weight-normal"><spring:message code="site.admin.page.home.page" /></h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="p-5" style="background-color: #00b5e9;">
                <h2 class="h1 text-white font-weight-normal">${memberCount}</h2>
                <h3 class="h5 text-uppercase text-white-50 font-weight-normal"><spring:message code="site.admin.page.home.member" /></h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="p-5" style="background-color: #fa4251;">
                <h2 class="h1 text-white font-weight-normal">${commentCount}</h2>
                <h3 class="h5 text-uppercase text-white-50 font-weight-normal"><spring:message code="site.admin.page.home.comment" /></h3>
            </div>
        </div>

    </div>

    <!--content-->
    <div class="row pt-3 my-3">
        <div class="col-md-12">

            <table class="table">
                <thead class="thead-dark text-uppercase text-black-50">
                    <tr>
                        <th><spring:message code="site.admin.page.home.name" /></th>
                        <th><spring:message code="site.admin.page.home.profession" /></th>
                        <th><spring:message code="site.admin.page.home.status" /></th>
                        <th><spring:message code="site.admin.page.home.date" /></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="person" items="${latest}">
                        <tr>
                            <td>${person.fullName()}</td>
                            <td>${person.profession}</td>

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

                            <fmt:parseDate value="${person.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdDate" />
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${createdDate}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

    </div>

</t:layout>
