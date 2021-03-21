<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout>
    <h2>${letter}</h2>

    <!--features-->
    <section class="row row-cols-1 row-cols-md-4">
        <c:if test="${!people.hasContent()}">
            <spring:message code="site.page.norecord" />
        </c:if>
        <c:forEach var="person" items="${people.content}">
            <!--features #${person.id}-->
            <div class="col mb-4">
                <div class="card h-100">
                    <img src="${person.photo}" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title"><a href="${person.link()}">${person.fullName()}</a></h5>
                        <h6 class="card-subtitle mb-2 text-muted">${person.profession}</h6>
                        <p class="card-text">${fn:substringBefore(person.description, ".")}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </section>

    <c:if test="${people.getTotalPages() > 1}">
        <!--pagination-->
        <nav aria-label="Page navigation">
            <ul class="pagination pagination-lg justify-content-center">
                <c:if test="${people.hasPrevious()}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${people.number - 1}"><spring:message code="site.pagination.previous" /></a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="0" end="${people.getTotalPages() - 1}">
                    <li class="page-item <c:if test="${people.number == i}">active</c:if>">
                        <a class="page-link" href="?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
                <c:if test="${people.hasNext()}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${people.number + 1}"><spring:message code="site.pagination.next" /></a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>

</t:layout>
