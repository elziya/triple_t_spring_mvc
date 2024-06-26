<%@tag description="deafult layout tag" pageEncoding="UTF-16"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainLayout title="Главная">
    <nav class="navbar navbar-expand-lg navbar-light text-white" style="background-color: #075e7f">
        <div class="container-fluid">
            <a class="navbar-brand text-white" style=" color: #F0CA65;font-weight: bold;">
                T&Ttracker
            </a>
            <form class="d-flex">
                <a href="${s:mvcUrl('SIC#getSignInPage').build()}" class="btn btn-outline-light me-2">Войти</a>
                <a href="${s:mvcUrl('SUC#getSignUpPage').build()}" class="btn btn-warning">Зарегистриоваться</a>
            </form>
        </div>
    </nav>
    <jsp:doBody/>
</t:mainLayout>
