<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3 " id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger " href="#page-top"><fmt:message key="header.title"/>
        </a>
        <a class="navbar-brand js-scroll-trigger train-icon" href="#page-top">
        </a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto my-2 my-lg-0">
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger cursor" data-toggle="modal"
                       data-target="#ModalLogin"><fmt:message key="header.login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger cursor" data-toggle="modal"
                       data-target="#ModalRegistration"><fmt:message key="header.registration"/></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="dropdown">
        <button class="btn navbar-brand " type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            <fmt:message key="header.selected-language"/>
        </button>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="language?locale=en"><fmt:message
                    key="header.change-language-english"/></a>
            <a class="dropdown-item"  href="language?locale=ru"><fmt:message
                    key="header.change-language-russian"/></a>
        </div>
    </div>
</nav>
</body>
</html>
