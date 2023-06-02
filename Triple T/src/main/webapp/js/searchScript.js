document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('tagName').addEventListener('change', function () {

        let tagName = document.getElementById('tagName').value;

        if (tagName.length === 0){
            return;
        }
        let request = new XMLHttpRequest();

        request.open('GET', '/search/byTagName?name=' + tagName, false);

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
                html += '<div class="card-header" style="font-weight: bold;"><a href="/projects/' + response[i]['project']['id'] +
                    '/tasks/'+response[i]['id']+'">'+response[i]['name']+'</a></div>';
                html += '<div class="card-body">';
                html += '<h5 class="card-title">Потрачено: ' + response[i]['duration'] + ' минут</h5>';
                html += '<p class="card-text">Из проекта <a href="/projects/' + response[i]['project']['id'] +
                    '">'+response[i]['project']['name']+'</a></p>';
                html += '</div>';

                html += '<ul class="list-group list-group-flush">';

                html += '<li class="list-group-item">';
                if (response[i]['tags'] !== null) {
                    for (let k = 0; k < response[i]['tags'].length; k++) {
                        html += '<span style="float: left;background-color: #99F0D8">' +
                            response[i]['tags'][k]['name'] + '</span>';
                    }
                }
                html += '</li>';

                html += '</ul></div>';
            }

            document.getElementById('project_cards').innerHTML = html;
        }
    })
});
