<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const NUMBER_REGEX = /^\d+$/;

            document.getElementById('addTask').addEventListener('click', function () {

                document.getElementById('message').innerText = '';

                let taskName = document.getElementById('taskName').value;
                let duration = document.getElementById('time').value;

                if (taskName.length === 0 || duration.length === 0){
                    document.getElementById('message').innerText = 'Поля с названием задания и времени должны быть заполнены';
                    return;
                }

                if(duration.match(NUMBER_REGEX) === null) {
                    document.getElementById('message').innerText = 'Время должно быть числом'
                    return;
                }

                let btn = document.getElementById('addTask');
                btn.setAttribute('disabled', true);

                let request = new XMLHttpRequest();

                let values = $('#tag-selection').val();

                if (values[0] === document.getElementById('selectTag').text){
                    values = null;
                }

                let body = {
                    "name" : taskName,
                    "duration" : duration,
                    "tags": values,
                };

                request.open('POST', ${s:mvcUrl('PC#getProjects').build()} + ${project.id} + '/tasks', false);
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
                    let response = JSON.parse(request.response);

                    let html = '<div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px">';
                    html += '<div class="card-header" style="font-weight: bold;">'+response['name']+'</div>';
                    html += '<div class="card-body">';
                    html += '<h5 class="card-title">Потрачено: ' + response['duration'] + ' минут</h5>';
                    html += '<p class="card-text">В период с ' + response['startDate'] + ' - ' + response['endDate'] + '</p>';
                    html += '</div>';

                    html += '<ul class="list-group list-group-flush">';

                    for (let j = 0; j < response['tasks'].length; j++) {
                        html += '<li class="list-group-item">';
                        html += '<span style="float: left"><a href="/projects/' + response['id'] + '/tasks/'
                            + response['tasks'][j]['id'] + '">'+ response['tasks'][j]['name'] + '</a></span>';
                        html += '<span style="float: right">' + response['tasks'][j]['duration'] + ' минут</span>';

                        if (response['tasks'][j]['tags'] !== null) {
                            for (let k = 0; k < response['tasks'][j]['tags'].length; k++) {
                                html += '<span style="float: right;background-color: #99F0D8">' +
                                    response['tasks'][j]['tags'][k]['name'] + '</span>';
                            }
                        }
                        html += '</li>';
                    }

                    html += '</ul></div>';

                    document.getElementById('project_cards').innerHTML = html;
                    document.getElementById('taskName').value = '';
                    document.getElementById('time').value = '';
                }
                btn.removeAttribute('disabled');
            })
        })
    </script>

    <div class="container-fluid" style="margin-top: 15px">
        <div class="d-flex">
            <input type="text" id="taskName" name="task" required placeholder="Напишите свою задачу" size="100">

        </div>
        <div class="d-flex">
            <select class="form-select" id="tag-selection" name="selectTag" multiple="multiple">
                <option id="selectTag" selected>Выберите тег</option>
                    <c:forEach items="${tags}" var="tag">
                        <option>${tag.name}</option>
                    </c:forEach>
            </select>
            <a href="${pageContext.request.contextPath}/tags" class="btn btn-outline-light me-2">Новый тег</a>
        </div>
        <div class="d-flex">
            <input id="time" name="time" required placeholder="Время в минутах">

            <button id='addTask' class="btn btn-outline-success" type="submit">Добавить</button>

            <h5 style="color: #075e7f" id="message"></h5>
        </div>
    </div>
    <br>
    <div id="project_cards">
        <div class="card border-info mb-3" style="max-width: 30rem; margin-left: 40px">
            <div class="card-header" style="font-weight: bold;">${project.name}</div>
            <div class="card-body">
                <h5 class="card-title">Потрачено: ${project.duration} минут</h5>
                <p class="card-text">В период с ${project.startDate} - ${project.endDate}</p>
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach items="${project.tasks}" var="task">
                    <li class="list-group-item">
                        <span style="float: left"><a href="${s:mvcUrl('PC#getProjects').build()}${project.id}/tasks/${task.id}">${task.name}</a></span>
                        <span style="float: right">${task.duration} минут</span>
                        <c:forEach items="${task.tags}" var="tag">
                            <span style="float: right;background-color: #99F0D8">${tag.name}</span>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <c:if test="${message != null}">
        <h3 style="color: #075e7f">${message}</h3>
    </c:if>

</t:mainHeader>
