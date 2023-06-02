<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<t:mainHeader>

    <script src="<c:url value="/js/searchScript.js"/>"></script>

    <div class="container-fluid" style="margin-top: 15px">
        <div>
            <input type="text" name="tagName" id="tagName" placeholder="Введите название тега, чтобы найти задания и проекты" size="100">
        </div>
    </div>
    <br>
    <div id="project_cards"></div>

</t:mainHeader>
