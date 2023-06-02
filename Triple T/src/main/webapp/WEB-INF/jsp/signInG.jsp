<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:headerFirst>
    <div class="d-flex justify-content-center align-items-center" style="margin-top: 10%">
        <form action="" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <h1 class="text-center align-items-center">Войдите</h1>
            <label for="email">Ваш Email:</label>
            <input name="email" type="email" class="form-control" id="email" required placeholder="myemail@gmail.ru"
                   value="${email}">

            <label for="password">Пароль:</label>
            <input name="password" type="password" class="form-control" id="password" aria-describedby="passwordHelp"
                   required placeholder="Введите пароль">
            <br>
            <button type="submit" class="w-100 btn btn-lg btn-primary">Войти</button>
            <c:if test="${error != null}">
                <h6 class="text-center errorMessage">Неправильный email или пароль</h6>
            </c:if>
            <a href="${vkRequest}">  Войти через ВКонтакте</a>
        </form>
    </div>
</t:headerFirst>
