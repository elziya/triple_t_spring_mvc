<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <script>
        document.addEventListener('DOMContentLoaded', function() {

            document.getElementById('tagName').addEventListener('change', function () {

                let tagName = document.getElementById('tagName').value;

                if (tagName.length === 0){
                    document.getElementById('message').innerText = 'Название тега должно быть указано';
                    return;
                }

                let btn = document.getElementById('addTag');
                btn.setAttribute('disabled', true);

                let request = new XMLHttpRequest();

                let body = {
                    "name" : tagName
                };

                request.open('POST', document.URL, false);
                request.setRequestHeader('Content-Type', 'application/json');
                request.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                request.send(
                    JSON.stringify(body)
                );

                if (request.status !== 200) {
                    let responseException = JSON.parse(request.response);

                    if (responseException['simple']){

                        document.getElementById('message').innerText = responseException['message'];
                    }
                    else {

                        let html = '<div class="d-flex justify-content-center align-items-center" style="margin-top: 10%">';
                        html += '<h1>' + responseException['status'] + '</h1></div>';
                        html += '<div class="d-flex justify-content-center align-items-center">';
                        html += '<h4>' + responseException['message'] + '</h4></div>'

                        document.getElementById('main').innerHTML = html;
                    }

                } else {

                    document.getElementById('message').innerText = '';

                    let html = '';
                    let response = JSON.parse(request.response);
                    for (let i = 0; i < response.length; i++) {
                        html += '<div class="card" style="width: 10rem;"><div class="card-body">';
                        html += '<h5 class="card-title">' + response[i]['name'] + '</h5>';
                        html += '</div></div>';
                    }
                    document.getElementById('tags').innerHTML = html;

                    document.getElementById('tagName').value = '';
                }

                btn.removeAttribute('disabled');

            });
        });
    </script>

    <div class="container-fluid" style="margin-top: 15px">
        <div>

            <input type="text" name="tagName" id="tagName" required placeholder="Название тега" style="size: 100px">

            <button id="addTag" class="btn btn-outline-success" type="submit">Создать</button>
        </div>
    </div>
    <br>
    <h5 style="color: #075e7f" id="message"></h5>
    <div id="tags">
        <c:forEach items="${tags}" var="tag">
            <div class="card" style="width: 10rem;">
                <div class="card-body">
                    <h5 class="card-title">${tag.name}</h5>
                </div>
            </div>
        </c:forEach>
    </div>

</t:mainHeader>
