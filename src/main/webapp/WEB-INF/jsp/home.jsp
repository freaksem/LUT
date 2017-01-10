<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <spring:url value="/css/main.css" var="springCss" />
    <spring:url value="/js/jquery.js" var="jQuery" />
    <link href="${springCss}" rel="stylesheet" />

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/login">Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <c:choose>
        <c:when test="${loggedUserName == null}">
            <p>Hello anonymous. Go to login page --> <a href="/login">here</a></p>
        </c:when>
        <c:otherwise>
            <span>Hello, <a href="/user">${loggedUserName}</a>!</span>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Sign Out"/>
            </form>
        </c:otherwise>
    </c:choose>


</div>

<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>