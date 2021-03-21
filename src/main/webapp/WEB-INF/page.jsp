<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:layout wide="true">
    <div class="bg-light py-3">
        <div class="container">
            <div class="row no-gutters">
                <div class="col pl-2">
                    <h1 class="text-success">${page.title}</h1>
                    <p class="lead text">${page.excerpt}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container my-3">
        ${page.description}
    </div>
</t:layout>
