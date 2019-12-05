<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Buy tickets</title>
    <base href="${pageContext.request.contextPath}/">
    <%@include file="../blocks/head.jsp" %>
</head>
<body id="page-top" class="body" onload="setLastSearch()">
<%@ include file="../blocks/header.jsp" %>

<div>

    <div class="background-image"></div>

    <!-- Page Content -->
    <section class="py-5">
        <div class="card flex-row my-5 card-center">
            <%@ include file="../blocks/toast.jsp" %>
            <div class="card-body">
                <h5 class="card-title text-center"><fmt:message key="tickets.order-tickets"/></h5>
                <hr style="margin-bottom: 3.5%">
                <div class="container">
                    <div class="row">
                        <div class="container">
                            <form id="form-flights">
                                <div class="row">
                                    <div class="col"><fmt:message key="tickets.where-from"/></div>
                                    <div class="col"></div>
                                    <div class="col"><fmt:message key="tickets.where"/></div>
                                    <div class="w-100 "></div>
                                    <select class="col custom-select custom-select-sm" name="departureStationSelector"
                                            id="departureStationSelector" style="margin-bottom: 3.5%">
                                        <c:forEach items="${stations}" var="station">
                                            <option value="${station.id}">${station.name}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="col" onclick="changeStation()">
                                        <div class="icon icon-change"
                                             style="width: 30px; margin-left: auto; margin-right: auto;cursor: pointer">
                                        </div>
                                    </div>
                                    <select class="col custom-select custom-select-sm" name="arrivalStationSelector"
                                            id="arrivalStationSelector" style="margin-bottom: 3.5%">
                                        <c:forEach items="${stations}" var="station">
                                            <option value="${station.id}">${station.name}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="w-100 "></div>
                                    <div class="col"><fmt:message key="tickets.departure-date"/></div>
                                    <div class="col"><fmt:message key="tickets.departure-time-from"/></div>
                                    <div class="col"></div>
                                    <div class="w-100 "></div>
                                    <div class='col border rounded input-group date'>
                                        <input type='text' class="form-control" name="date" id='datetimepicker'
                                               style="border: none;"/>
                                        <span class="input-group-addon icon icon-calendar"
                                              style="margin-top: 1%; margin-left: 4%">
                                    </span>
                                    </div>
                                    <div class="col">
                                        <div class=' border rounded input-group date'>
                                            <input type='text' class="form-control" name="time" id='datetimepicker1'
                                                   style="border: none"/>
                                            <span class="input-group-addon icon icon-time"
                                                  style="margin-top: 1%; margin-left: 4%">
                                            </span>
                                        </div>

                                    </div>
                                    <div class="col"></div>
                                    <div class="w-100 "></div>
                                    <div class="col"></div>
                                    <div class="col"></div>
                                    <div class="col"></div>
                                    <div class="w-100" style="margin-top: 30px"></div>
                                    <div class="col"></div>
                                    <div class="col">
                                        <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit">
                                            <fmt:message key="tickets.searc"/></button>
                                    </div>
                                    <div class="col"></div>
                                    <div class="w-100" style="margin-top: 30px"></div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <c:choose>
                    <c:when test="${not empty requestScope.flights}">
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <th scope="col">#<fmt:message key="tickets.train"/></th>
                                <th scope="col"><fmt:message key="tickets.from-where-to-where"/></th>
                                <th scope="col"><fmt:message key="tickets.date"/></th>
                                <th scope="col"><fmt:message key="tickets.free-places"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flights}" var="flight">
                                    <tr id="flight_row" onclick="openSeatModal(${flight.id})" class="cursor">
                                        <th scope="row">${flight.trainName}</th>
                                        <td>${flight.departureStation} / ${flight.arrivalStation}
                                        </td>
                                        <td>${flight.departureTime} / ${flight.arrivalTime}</td>
                                        <td>${flight.freeSeatNumber}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:when test="${not empty requestScope.message}">
                        <div class="container">
                            <div class="row">
                                <div class="col" style="text-align: center">
                                    Рейсов по заданному пути не найдено!
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>

            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="ModalSeat" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <h5 class="card-title text-center"><fmt:message key="tickets.modal.ticket-order"/></h5>
                <form class="form-seat">


                    <div class="form-group">
                        <label for="wagon"><fmt:message key="tickets.modal.choose-car"/></label>
                        <select class="col custom-select custom-select-sm" id="wagon"
                                onchange="selectWagon(this.options[this.selectedIndex].value)">
                        </select>

                    </div>

                    <div class="form-group">
                        <label for="seat"><fmt:message key="tickets.modal.choose-seat"/></label>
                        <select class="col custom-select custom-select-sm" id="seat" readonly>
                            <option selected>Select wagon!</option>
                        </select>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit"><fmt:message
                            key="tickets.modal.order"/></button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        <fmt:message key="tickets.modal.cancel"/></a>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../blocks/registration-modal.jsp" %>
<%@ include file="../blocks/login-modal.jsp" %>
<%@ include file="../blocks/scripts.jsp" %>
</body>
</html>

