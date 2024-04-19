<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@tag description="footer" pageEncoding="UTF-8" %>
<footer class="bg-light">

    <div class="container">
        <div class="row py-4">

            <div class="col-md-4 py-3">
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <select class="form-control text-capitalize" id="theme" name="theme" onchange="selectedIndex > 0 ? location = '?theme=' + value : ''">
                            <option selected="selected"><spring:message code="site.footer.pleaseselect" /></option>
                            <c:forEach items="${themeList}" var="theme">
                                <option value="${theme}">${theme}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li class="list-inline-item"><a href="?locale=tr" class="text-danger"><spring:message code="site.footer.turkish" /></a></li>
                    <li class="list-inline-item"><a href="?locale=en" class="text-primary"><spring:message code="site.footer.english" /></a></li>
                </ul>
                <a href="https://www.yusufsezer.com" target="_blank">Yusuf Sezer</a> - &copy; <fmt:formatDate pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>" />
            </div>

            <div class="col">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb bg-light justify-content-end">
                        <li class="breadcrumb-item"><a href="."><spring:message code="site.footer.home" /></a></li>
                        <li class="breadcrumb-item"><a href="login"><spring:message code="site.footer.login" /></a></li>
                        <li class="breadcrumb-item"><a href="register"><spring:message code="site.footer.register" /></a></li>
                        <c:forEach items="${pageList}" var="page">
                            <li class="breadcrumb-item"><a href="page/${page.slug}">${page.title}</a></li>
                        </c:forEach>
                    </ol>
                </nav>
            </div>

        </div>
    </div>

</footer>

<!-- JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
