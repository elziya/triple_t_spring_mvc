<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px; margin-top: 50px">
        <div class="card-header" style="font-weight: bold;">${task.name}</div>
        <div class="card-body">
            <h5 class="card-title">Потрачено: ${task.duration} минут</h5>
            <p class="card-text">
                <c:forEach items="${task.tags}" var="tag">
                    <span style="float: left;background-color: #99F0D8">${tag.name}</span>
                </c:forEach>
                <span style="float: right">
                    <a style="color: #6e8a97" href="${s:mvcUrl('PC#getProjects').build()}${task.projectId}/tasks/${task.id}/upload">
                        Загрузить файл
                    </a>
                </span>
            </p>
        </div>
        <ul class="list-group list-group-flush">
            <c:forEach items="${task.files}" var="file">
                <li class="list-group-item">
                    <span style="float: left"><a href="/files/${file.id}">${file.originalName}</a></span>
                </li>
            </c:forEach>
        </ul>
    </div>

    <c:if test="${message != null}">
        <h3 style="color: #075e7f">${message}</h3>
    </c:if>

</t:mainHeader>
