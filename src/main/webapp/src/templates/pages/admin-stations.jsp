<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>admin page</title>
    <base href="${pageContext.request.contextPath}/">
    <%@include file="../blocks/head.jsp" %>
</head>
<body class="body">
<%@ include file="../blocks/header.jsp" %>
<div class="background-image"></div>

<section class="py-5">
    <div class="card flex-row"
         style="width: 80%; margin-left: auto; margin-right: auto; margin-top: 5%">
        <div class="card-body" style="padding: 0">

            <div class="container-fluid" style="padding: 0">
                <h5 class="card-title text-center" style="margin-top: 1%"><fmt:message key="admin"/></h5>
                <hr style="margin-bottom: 0">
            </div>
            <div class="container-fluid" style="padding: 0">
                <div id="wrapper">

                    <!-- Sidebar -->
                    <ul class="sidebar navbar-nav rounded-left ">
                        <li class="nav-item">
                            <a class="nav-link" href="admin-flights">
                                <span><fmt:message key="admin.flights"/></span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="admin-stations">
                                <span><fmt:message key="admin.stations"/></span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="trains">
                                <span><fmt:message key="admin.trains"/></span>
                            </a>
                        </li>
                    </ul>

                    <div id="content-wrapper">

                        <div class="container">
                            <div style="width: 20%; cursor: pointer; margin: 3% auto;">
                                <a class="btn btn-dark btn-xl" style="color: white" data-toggle="modal"
                                   data-target="#ModalStation"><fmt:message key="admin.add-station"/></a>
                            </div>
                        </div>
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="admin.name-station"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${stations}" var="station">
                                    <tr>
                                        <td>${station.name}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<div class="modal fade" id="ModalStation" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <h5 class="card-title text-center"><fmt:message key="admin.add-station"/></h5>
                <form class="form-seat" id="add-station-form">

                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" id="station" class="form-control" placeholder="Название станции"
                                   required autofocus>
                            <label for="station"><fmt:message key="admin.name-station"/></label>
                        </div>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit"><fmt:message
                            key="admin.add"/></button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        <fmt:message key="tickets.modal.cancel"/></a>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../blocks/toast.jsp" %>
<%@ include file="../blocks/scripts.jsp" %>
</body>
</html>
