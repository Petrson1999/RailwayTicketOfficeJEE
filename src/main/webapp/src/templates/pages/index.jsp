<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Railway ticket office</title>
    <base href="${pageContext.request.contextPath}/">
    <%@include file="../blocks/head.jsp"%>
</head>
<body id="page-top">
<%@ include file="../blocks/header.jsp" %>
<header>
    <%--carousel--%>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active"
                 style="background-image: url('http://www.citymetric.com/sites/default/files/article_body_2017/06/screen_shot_2017-06-21_at_18.02.44.png')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><fmt:message key="slider.first-slide.title"/><br></h2>
                    <p class="lead"><fmt:message key="slider.first-slide.description"/></p>
                    <a class="btn btn-light-forlend btn-xl js-scroll-trigger" href="#first-slide-info"><fmt:message
                            key="slider.button.read-more"/></a>
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url('https://s2.best-wallpaper.net/wallpaper/2560x1440/1807/France-Limoges-city-night-train-station_2560x1440.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><fmt:message key="slider.second-slide.title"/><br></h2>
                    <p class="lead"><fmt:message key="slider.second-slide.description"/></p>
                    <a class="btn btn-light-forlend btn-xl js-scroll-trigger" href="#second-slide-info"><fmt:message
                            key="slider.button.read-more"/></a>
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url('http://wp.widewallpapers.ru/2k/cities/tokyo/1920x1080/Tokyo-1920x1080-095.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><fmt:message key="slider.third-slide.title"/><br></h2>
                    <p class="lead"><fmt:message key="slider.third-slide.description"/></p>
                    <a class="btn btn-light-forlend btn-xl js-scroll-trigger" href="#third-slide-info"><fmt:message
                            key="slider.button.read-more"/></a>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <!-- second-slide-info -->
    <section class="page-section " id="first-slide-info">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center">
                    <h2 class="text-white mt-0"><fmt:message key="slider.first-slide.title"/></h2>
                    <hr class="divider light my-4">
                    <p class="text-white-50 mb-4"><fmt:message key="slider.first-slide.info"/></p>
                </div>
            </div>
        </div>
    </section>

    <!-- second-slide-info -->
    <section class="page-section " id="second-slide-info">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center">
                    <h2 class="text-white mt-0"><fmt:message key="slider.second-slide.title"/></h2>
                    <hr class="divider light my-4">
                    <p class="text-white-50 mb-4"><fmt:message key="slider.second-slide.info"/></p>
                </div>
            </div>
        </div>
    </section>

    <!-- third-slide-info -->
    <section class="page-section " id="third-slide-info">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 text-center">
                    <h2 class="text-white mt-0"><fmt:message key="slider.third-slide.title"/></h2>
                    <hr class="divider light my-4">
                    <p class="text-white-50 mb-4"><fmt:message key="slider.third-slide.info"/></p>
                </div>
            </div>
        </div>
    </section>
</header>


<%@ include file="../blocks/scripts.jsp" %>
<%@ include file="../blocks/registration-modal.jsp" %>
<%@ include file="../blocks/login-modal.jsp" %>
</body>
</html>
