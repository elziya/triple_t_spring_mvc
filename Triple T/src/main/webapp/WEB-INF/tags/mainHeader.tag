<%@tag description="deafult layout tag" pageEncoding="UTF-16"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainLayout title="Рабочая">
    <div class="row">
        <div class="col-2" style="background-color: #eae6e0; border: #075e7f">
            <div class="flex p-3 text-white" >
                <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
                    <span class="fs-5 fw-semibold" style="color:#075e7f; font-weight: bold;">T&Ttracker</span>
                </a>
                <ul class="list-unstyled ps-0">
                    <li class="mb-1">
                        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                            Проекты
                        </button>
                        <div id="dashboard-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="${s:mvcUrl('PC#getProjects').build()}" class="link-dark rounded">Создать новые/перейти к проектам</a></li>
                                <li><a href="${s:mvcUrl('SC#getSearchPage').build()}" class="link-dark  rounded">Найти по тегам</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="mb-1">
                        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                            Теги
                        </button>
                        <div id="orders-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="${s:mvcUrl('TC#getTags').build()}" class="link-dark  rounded">Добавить новые</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="border-top my-3"></li>
                    <li class="mb-1">
                        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
                            Аккаунт
                        </button>
                        <div id="account-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <li><a href="${s:mvcUrl('PC#getProfilePage').build()}" class="link-dark  rounded">Профиль</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-10">
            <jsp:doBody/>
        </div>
    </div>
</t:mainLayout>
