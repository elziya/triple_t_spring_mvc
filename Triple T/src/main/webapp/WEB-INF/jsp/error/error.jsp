<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:mainLayout title="${status}">
    <div class="d-flex justify-content-center align-items-center" style="margin-top: 10%">
        <h1>${status}</h1>
    </div>
    <div class="d-flex justify-content-center align-items-center">
        <h4>${message}</h4>
    </div>
</t:mainLayout>