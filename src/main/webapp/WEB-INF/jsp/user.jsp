<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<spring:url value="/css/main.css" var="springCss" />
	<spring:url value="/js/jquery.js" var="jQuery" />
	<link href="${springCss}" rel="stylesheet" />

</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Spring Boot</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="/login">Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">

        <div class="starter-template">
            <h1>Exchange application</h1>
            <p>Message from custom properties: ${message2}</p>

            <p>Hello, ${loggedUserName}!</p>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Sign Out"/>
            </form>

        </div>

        <h2>Users list:</h2>
        <c:forEach items="${users}" var="user">
            <p><c:out escapeXml="false" value="${user.userName}"/></p>
        </c:forEach>

    </div>

    <script type="text/javascript" src="${jQuery}"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>