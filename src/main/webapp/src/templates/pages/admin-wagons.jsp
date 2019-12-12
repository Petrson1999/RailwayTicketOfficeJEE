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
                        <li class="nav-item ">
                            <a class="nav-link" href="admin-stations">
                                <span><fmt:message key="admin.stations"/></span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="admin-trains">
                                <span><fmt:message key="admin.trains"/></span>
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="admin-wagons">
                                <span><fmt:message key="admin.wagons"/></span>
                            </a>
                        </li>
                    </ul>

                    <div id="content-wrapper">

                        <div class="container">
                            <div style="width: 20%; cursor: pointer; margin: 3% auto;">
                                <a class="btn btn-dark btn-xl" style="color: white" data-toggle="modal"
                                   data-target="#ModalWagonType"><fmt:message key="admin.add-wagon-type"/></a>
                            </div>
                        </div>
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="admin.wagon-type-name"/></th>
                                    <th scope="col"><fmt:message key="admin.wagon-seats-count"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.wagon_types}" var="type">
                                    <tr>
                                        <td>${type.name}</td>
                                        <td>${type.numberOfSeats}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>





                        <div class="container">
                            <div style="width: 20%; cursor: pointer; margin: 3% auto;">
                                <a class="btn btn-dark btn-xl" style="color: white" data-toggle="modal"
                                   data-target="#ModalWagon"><fmt:message key="admin.add-wagon"/></a>
                            </div>
                        </div>
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="admin.add-wagon-name"/></th>
                                    <th scope="col"><fmt:message key="admin.wagon-type-name"/></th>
                                    <th scope="col"><fmt:message key="admin.wagon-seats-count"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.wagons}" var="wagon">
                                    <tr>
                                        <td>${wagon.name}</td>
                                        <td>${wagon.wagonType}</td>
                                        <td>${wagon.seats.size()}</td>
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

<div class="modal fade" id="ModalWagonType" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <%@ include file="../blocks/toast.jsp" %>
                <h5 class="card-title text-center"><fmt:message key="admin.wagon-type-name"/></h5>
                <form class="form-seat" id="add-wagon-type-form">

                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" id="type-name" class="form-control" placeholder=
                            <fmt:message key="admin.wagon-type-name"/>
                                    required autofocus>
                            <label for="type-name"><fmt:message key="admin.wagon-type-name"/></label>
                        </div>
                    </div>
                    <input type="number" class="form-control" placeholder=
                    <fmt:message key="admin.wagon-seats-count"/> id="seats-count" style="margin-bottom: 3%">
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

<div class="modal fade" id="ModalWagon" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <%@ include file="../blocks/toast.jsp" %>
                <h5 class="card-title text-center"><fmt:message key="admin.wagon-type-name"/></h5>
                <form class="form-seat" id="add-wagon-form">

                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" id="wagon-name" class="form-control" placeholder=
                            <fmt:message key="admin.add-wagon-name"/>
                                    required autofocus>
                            <label for="wagon-name"><fmt:message key="admin.add-wagon-name"/></label>
                        </div>
                    </div>
                    <select class="col custom-select custom-select-sm" name="arrivalStationSelector"
                            id="wagon-types" style="margin-bottom: 3.5%">
                        <c:forEach items="${requestScope.wagon_types}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
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
