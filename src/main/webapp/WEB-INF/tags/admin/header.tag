<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@tag description="header" pageEncoding="UTF-8"%>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">

        <a class="navbar-brand" href=".">
            <svg xmlns="http://www.w3.org/2000/svg" width="200" height="50" viewBox="0 0 503 105.282">
                <text id="Spring_Biography" data-name="Spring Biography" transform="translate(91 73)" fill="#f9f9f9" font-size="50" font-family="SegoeUI-Bold, Segoe UI" font-weight="500">
                    <tspan x="0" y="0"><c:out value="${title}" escapeXml="true" /></tspan>
                </text>
                <g id="leaf" transform="translate(-62.605 0)">
                    <path id="Path_1" data-name="Path 1" d="M120.489,0s9.3,22.754-30.561,38.038c-21.66,8.3-38.3,29.583-18.433,62.455C73.14,85.8,80.194,53.4,110.525,46.01c0,0-28.23,10.556-30.724,58.339,14.506,2.037,48.527,3.405,58.627-26.118C151.766,39.224,120.489,0,120.489,0Z" transform="translate(0 0)" fill="#3a7f0d" />
                    <path id="Path_2" data-name="Path 2" d="M79.814,104.15A90.062,90.062,0,0,0,136.109,28.7,114.9,114.9,0,0,0,120.489,0s9.3,22.754-30.561,38.038c-21.66,8.3-38.3,29.583-18.433,62.455C73.14,85.8,80.194,53.4,110.525,46.01,110.524,46.01,82.377,56.54,79.814,104.15Z" transform="translate(0 0)" fill="#49a010" />
                    <circle id="Ellipse_1" data-name="Ellipse 1" cx="2.879" cy="2.879" r="2.879" transform="translate(65.543 33.039)" fill="#3a7f0d" />
                    <g id="Group_1" data-name="Group 1" transform="translate(70.479 8.363)">
                        <circle id="Ellipse_2" data-name="Ellipse 2" cx="2.056" cy="2.056" r="2.056" transform="translate(4.935 22.619)" fill="#49a010" />
                        <circle id="Ellipse_3" data-name="Ellipse 3" cx="5.141" cy="5.141" r="5.141" transform="translate(0)" fill="#49a010" />
                        <circle id="Ellipse_4" data-name="Ellipse 4" cx="1.645" cy="1.645" r="1.645" transform="translate(19.329 15.216)" fill="#49a010" />
                        <circle id="Ellipse_5" data-name="Ellipse 5" cx="4.729" cy="4.729" r="4.729" transform="translate(30.023 2.879)" fill="#49a010" />
                    </g>
                </g>
            </svg>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link ${dashboard ? 'active' : ''}" href=".">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bar-chart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5h-2v12h2V2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z" />
                        </svg>
                        <spring:message code="site.admin.header.nav.dashboard" />
                        <span class="sr-only">(<spring:message code="site.admin.header.nav.current" />)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${bio ? 'active' : ''}" href="bio">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                        </svg>
                        <spring:message code="site.admin.header.nav.biographies" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pages ? 'active' : ''}" href="pages">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-list-ul" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                        </svg>
                        <spring:message code="site.admin.header.nav.pages" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${members? 'active' : ''}" href="members">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-people-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z" />
                        </svg>
                        <spring:message code="site.admin.header.nav.members" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${comments? 'active' : ''}" href="comments">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-dots-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                        </svg>
                        <spring:message code="site.admin.header.nav.comments" />
                    </a>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link ${settings? 'active' : ''}" href="settings">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-gear-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311c.446.82.023 1.841-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705 1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413 1.4-2.397 0-2.81l-.34-.1a1.464 1.464 0 0 1-.872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 0 0-5.86 2.929 2.929 0 0 0 0 5.858z" />
                        </svg>
                    </a>
                </li>
                <li class="nav-item p-2 text-white"><sec:authentication property="principal.Username" /></li>
                <li class="nav-item p-2">
                    <a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/logout" class="text-white">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-box-arrow-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z" />
                            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z" />
                        </svg>
                    </a>
                </li>
            </ul>

        </div>
    </nav>
</header>
