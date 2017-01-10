<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li class="active"><a href="/login">Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    
        <form role="form" action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <label for="userName">Логин</label>
                <input type="text" name="userName" id="userName" required autofocus>
            </div>
            <div>
                <label for="password">Пароль</label>
                <input type="password" name="password" id="password" required>
            </div>
            <button type="submit">Вход</button>
        </form>

    <c:choose>
        <c:when test="${error.isPresent()}">
            <p>The userName or password you have entered is invalid, try again.</p>
            <br />
        </c:when>
    </c:choose>


</div>

<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>