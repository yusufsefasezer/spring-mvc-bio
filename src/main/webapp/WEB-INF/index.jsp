<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout>
    <h2>Random</h2>
    
    <!--features-->
    <section class="row row-cols-1 row-cols-md-4">
        <c:forEach var="person" items="${feature}">
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

    <!--content-->
    <section class="row">
        <section class="col-md">

            <h2><spring:message code="site.main.latest" /></h2>

            <div class="row row-cols-1 row-cols-md-4">
                <c:forEach var="person" items="${latest}">
                    <!--person #${person.id}-->
                    <div class="col mb-4">
                        <div class="card h-100">
                            <img src="${person.photo}" class="card-img-top" alt="${person.profession}">
                            <div class="card-body">
                                <h5 class="card-title"><a href="${person.link()}">${person.fullName()}</a></h5>
                                <h6 class="card-subtitle mb-2 text-muted">${person.profession}</h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </section>

        <aside class="col-md-3">
            <div class="card">

                <div class="card-header"><spring:message code="site.sidebar.popular" /></div>

                <div class="card-body">
                    <ul class="list-unstyled">
                        <c:forEach var="person" items="${popular}">
                            <li class="media my-3">
                                <img src="${person.photo}" class="img-thumbnail mr-3" width="100" />
                                <div class="media-body">
                                    <a href="${person.link()}">${person.fullName()}</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </aside>
    </section>
</t:layout>
