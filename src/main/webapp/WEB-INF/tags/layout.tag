<%@tag description="layout" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@attribute name="wide" %>
<!DOCTYPE html>
<html>

    <t:head />

    <body>

        <!--header-->
        <t:header />

        <!--main-->
        <main <c:if test="${wide == null}">class="container py-3"</c:if>>
            <!--<main>-->
            <jsp:doBody />
        </main>

        <!--footer-->
        <t:footer />

    </body>
</html>
