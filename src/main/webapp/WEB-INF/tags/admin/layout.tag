<%@tag description="admin layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/admin" %>
<!DOCTYPE html>
<html>

    <t:head />

    <body class="bg-light">

        <!--header-->
        <t:header />

        <!--main-->
        <main class="container pt-3">
            <!--breadcrumb-->
            <t:breadcrumb />
            <jsp:doBody />
        </main>

        <!--footer-->
        <t:footer />

    </body>
</html>
