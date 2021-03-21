<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@tag description="head" pageEncoding="UTF-8" %>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
    <!-- meta tags-->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
    <meta name="author" content="Yusuf SEZER" />

    <!-- title -->
    <title><c:out value="${title}" escapeXml="true" /></title>

    <!-- css -->
    <link rel="stylesheet" href="<spring:theme code="style-url" text="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" />
    <style>
        a {
            color: var(--green) !important;
        }
    </style>
</head>
