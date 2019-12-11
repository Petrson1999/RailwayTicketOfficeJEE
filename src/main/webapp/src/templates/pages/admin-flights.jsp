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
                        <li class="nav-item active">
                            <a class="nav-link" href="admin-flights">
                                <span><fmt:message key="admin.flights"/></span></a>
                        </li>
                        <li class="nav-item ">
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
                                   data-target="#ModalFlight"><fmt:message key="admin.add-flight"/></a>
                            </div>
                        </div>
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">#<fmt:message key="tickets.train"/></th>
                                    <th scope="col"><fmt:message key="tickets.from-where-to-where"/></th>
                                    <th scope="col"><fmt:message key="tickets.date"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flights}" var="flight">
                                    <tr id="flight_row"
                                        onclick="openSeatModal(${flight.id},'${flight.departureStation.toString()}'+' / '+'${flight.arrivalStation.toString()}','${flight.formatedDepartureTime.toString()}'+' / '+'${flight.formatedArrivalTime.toString()}')"
                                        class="cursor">
                                        <th scope="row">${flight.trainName}</th>
                                        <td>${flight.departureStation} / ${flight.arrivalStation}
                                        </td>
                                        <td>${flight.formatedDepartureTime} / ${flight.formatedArrivalTime}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- /.content-wrapper -->

                </div>
            </div>
        </div>
    </div>

</section>

<div class="modal fade" id="ModalFlight" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content" style="min-width: 130%">
            <div class="card-body" style=" height: 44em">
                <%@ include file="../blocks/toast.jsp" %>
                <h5 class="card-title text-center"><fmt:message key="admin.adding-flight"/></h5>
                <form class="form-flight" id="add-flights">

                    <div class="form-group">
                        <div class="container">
                            <div class="row">
                                <div class="container">
                                    <div class="row">
                                        <div class="col"><fmt:message key="tickets.where-from"/> :</div>
                                        <div class="col"><fmt:message key="tickets.where"/> :</div>
                                        <div class="w-100" style="margin: 2%"></div>
                                        <select class="col custom-select custom-select-sm" name="departureStationSelector"
                                                id="departureStationSelector" style="margin-bottom: 3.5%">
                                            <c:forEach items="${stations}" var="station">
                                                <option value="${station.id}">${station.name}</option>
                                            </c:forEach>
                                        </select>
                                        <select class="col custom-select custom-select-sm" name="arrivalStationSelector"
                                                id="arrivalStationSelector" style="margin-bottom: 3.5%">
                                            <c:forEach items="${stations}" var="station">
                                                <option value="${station.id}">${station.name}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col"><fmt:message key="admin.departure-date"/> :</div>
                                        <div class="col"><fmt:message key="admin.arrival-date"/> :</div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col border rounded input-group date">
                                            <input type='text' class="form-control" id='date-departure'
                                                   style="border: none;"/>
                                            <span class="input-group-addon icon icon-calendar"
                                                  style="margin-top: 1%; margin-left: 4%">
                                            </span>
                                        </div>
                                        <div class='col border rounded input-group date'>
                                            <input type='text' class="form-control" id='date-arrival'
                                                   style="border: none;"/>
                                            <span class="input-group-addon icon icon-calendar"
                                                  style="margin-top: 1%; margin-left: 4%">
                                            </span>
                                        </div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col"><fmt:message key="admin.departure-time"/> :</div>
                                        <div class="col"><fmt:message key="admin.arrival-time"/> :</div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col" style="padding: 0">
                                            <div class='border rounded input-group date'>
                                                <input type='text' class="form-control" id='departure-time'
                                                       style="border: none"/>
                                                <span class="input-group-addon icon icon-time"
                                                      style="margin-top: 1%; margin-left: 4%">
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col" style="padding: 0">
                                            <div class='border rounded input-group date'>
                                                <input type='text' class="form-control" id='arrival-time'
                                                       style="border: none"/>
                                                <span class="input-group-addon icon icon-time"
                                                      style="margin-top: 1%; margin-left: 4%; margin-right: 4%">
                                                </span>
                                            </div>
                                        </div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col"><fmt:message key="tickets.train"/> :</div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class=" col row" style="width: 100%">
                                            <select class="col custom-select custom-select-sm" name="trainSelector"
                                                    id="trainSelector" >
                                                <c:forEach items="${trains}" var="train">
                                                    <option value="${train.id}">${train.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="w-100 "></div>
                                        <div class="col"><fmt:message key="admin.cost"/> :</div>
                                        <input type="number" class="form-control" placeholder="cost" id="cost">

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit" style="margin-top: 20px">
                        <fmt:message key="admin.add"/>
                    </button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer;"
                       data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        <fmt:message key="tickets.modal.cancel"/></a>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../blocks/scripts.jsp" %>
</body>
</html>
