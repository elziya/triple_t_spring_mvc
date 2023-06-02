<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <div class="container-fluid" style="margin-top: 15px">

        <form id="form" method='post' enctype="multipart/form-data">
            <div class="d-flex">
                <h4 style="color: #075e7f">Загрузите файл к задаче</h4>
            </div>
            <div class="d-flex">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="file" name="files" multiple>
                <input id="upLoadFile" class="btn btn-outline-success" type="submit" value="Загрузить">
            </div>
        </form>
    </div>

    <c:if test="${message != null}">
        <h3 style="color: #075e7f">${message}</h3>
    </c:if>

</t:mainHeader>
