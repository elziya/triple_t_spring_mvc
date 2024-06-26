<%@tag description="deafult layout tag" pageEncoding="UTF-16"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <t:head/>
    </head>
    <body class="body">
        <div id="main">
            <jsp:doBody/>
            <t:footer/>
        </div>
    </body>
</html>
