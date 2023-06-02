<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <script>
        document.addEventListener('DOMContentLoaded', function() {

            document.getElementById('addProject').addEventListener('click', function () {

                document.getElementById('message').innerText = '';
                let projectName = document.getElementById('projectName').value;

                if (projectName.length === 0){
                    document.getElementById('message').innerText = 'Название проекта должно быть указано';
                    return;
                }

                let btn = document.getElementById('addProject');
                btn.setAttribute('disabled', true);

                let request = new XMLHttpRequest();

                let body = {
                    "name" : projectName
                };

                request.open('POST', document.URL, false);
                request.setRequestHeader('Content-Type', 'application/json');
                request.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');

                request.send(
                    JSON.stringify(body)
                );

                if (request.status !== 200) {
                    let responseException = JSON.parse(request.response);

                    let html = '<div class="d-flex justify-content-center align-items-center" style="margin-top: 10%">';
                    html += '<h1>' + responseException['status'] + '</h1></div>';
                    html += '<div class="d-flex justify-content-center align-items-center">';
                    html += '<h4>' + responseException['message'] + '</h4></div>'

                    document.getElementById('main').innerHTML = html;
                } else {
                    let html = '';
                    let response = JSON.parse(request.response);
                    for (let i = 0; i < response.length; i++) {
                        html += '<div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px">';
                        html += '<div class="card-header" style="font-weight: bold;"><a href="/projects/' + response[i]['id'] + '">'+response[i]['name']+'</a>' +
                            '<button id="deleteProject" class="btn btn-danger" style="float: right" onclick="deleteProject(' + response[i]['id'] + ')">Удалить</button></div>';
                        html += '  <div class="card-body">';
                        html += '<h5 class="card-title">Потрачно: ' + response[i]['duration'] + ' минут</h5>';
                        html += '<p class="card-text">В период с ' + response[i]['startDate'] + ' - ' + response[i]['endDate'] + '</p>';
                        html += '</div></div>';
                    }
                    document.getElementById('project_cards').innerHTML = html;

                    document.getElementById('projectName').value = '';
                }
                btn.removeAttribute('disabled');
            })
        });

        function deleteProject(projectId) {

            let btn = document.getElementById('deleteProject');
            btn.setAttribute('disabled', true);

            let request = new XMLHttpRequest();

            request.open('DELETE', ${s:mvcUrl('PC#getProjects').build()} + projectId, false);
            request.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
            request.send();

            if (request.status !== 200) {
                let responseException = JSON.parse(request.response);

                let html = '<div class="d-flex justify-content-center align-items-center" style="margin-top: 10%">';
                html += '<h1>' + responseException['status'] + '</h1></div>';
                html += '<div class="d-flex justify-content-center align-items-center">';
                html += '<h4>' + responseException['message'] + '</h4></div>'

                document.getElementById('main').innerHTML = html;
            } else {
                let html = '';
                let response = JSON.parse(request.response);
                for (let i = 0; i < response.length; i++) {
                    html += '<div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px">';
                    html += '<div class="card-header" style="font-weight: bold;"><a href="/projects/' + response[i]['id'] + '">'+response[i]['name']+'</a>' +
                        '<button id="deleteProject" class="btn btn-danger" style="float: right" onclick="deleteProject(' + response[i]['id'] + ')">Удалить</button></div>';
                    html += '  <div class="card-body">';
                    html += '<h5 class="card-title">Потрачно: ' + response[i]['duration'] + ' минут</h5>';
                    html += '<p class="card-text">В период с ' + response[i]['startDate'] + ' - ' + response[i]['endDate'] + '</p>';
                    html += '</div></div>';
                }
                document.getElementById('project_cards').innerHTML = html;
            }
            btn.removeAttribute('disabled');
        }
    </script>

    <div class="container-fluid" style="margin-top: 15px">
        <div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="text" name="projectName" id="projectName" required placeholder="Название проекта" style="size: 100px" size="100">

            <button id="addProject" class="btn btn-outline-success" type="submit">Создать</button>

            <h5 style="color: #075e7f" id="message"></h5>
        </div>
    </div>
    <br>
    <div id="project_cards">
    <c:forEach items="${projects}" var="project">
        <div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px">
            <div class="card-header" style="font-weight: bold;">
                <a href="${s:mvcUrl('PC#getProjects').build()}${project.id}">${project.name}</a>
                <button id="deleteProject" class="btn btn-danger" style="float: right" onclick="deleteProject(${project.id})">Удалить</button>
            </div>
            <div class="card-body">
                <h5 class="card-title">Потрачно: ${project.duration} минут</h5>
                <p class="card-text">В период с ${project.startDate} - ${project.endDate}</p>
            </div>
        </div>
    </c:forEach>
    </div>

    <c:if test="${message != null}">
        <h3 style="color: #075e7f">${message}</h3>
    </c:if>

</t:mainHeader>
