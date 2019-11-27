<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Buy tickets</title>
    <base href="${pageContext.request.contextPath}/">
    <%@include file="../blocks/head.jsp"%>
</head>
<body id="page-top" class="body">
<%@ include file="../blocks/header.jsp" %>

<div>


    <div class="background-image"></div>

    <!-- Page Content -->
    <section class="py-5">
        <div class="card flex-row my-5 card-center">
            <div class="card-body">
                <h5 class="card-title text-center"><fmt:message key="tickets.order-tickets"/></h5>
                <hr>
                <div class="container">
                    <div class="row">
                        <div class="container">
                            <div class="row">
                                <div class="col"><fmt:message key="tickets.where-from"/></div>
                                <div class="col"></div>
                                <div class="col"><fmt:message key="tickets.where"/></div>
                                <div class="w-100 "></div>
                                <select class="col selectpicker border rounded" data-live-search="true">
                                    <option class="bg-dark" style="color: white">Станция1</option>
                                    <option class="bg-dark" style="color: white">Станция2</option>
                                    <option class="bg-dark" style="color: white">Станция3</option>
                                </select>
                                <div class="col">
                                    <div class="icon icon-change"
                                         style="width: 30px; margin-left: auto; margin-right: auto;cursor: pointer">
                                    </div>
                                </div>
                                <select class="col selectpicker border rounded " data-live-search="true">
                                    <option class="bg-dark" style="color: white">Станция1</option>
                                    <option class="bg-dark" style="color: white">Станция2</option>
                                    <option class="bg-dark" style="color: white">Станция3</option>
                                </select>
                                <div class="w-100 "></div>
                                <div class="col"><fmt:message key="tickets.departure-date"/></div>
                                <div class="col"><fmt:message key="tickets.departure-time-from"/></div>
                                <div class="col"></div>
                                <div class="w-100 "></div>
                                <div class='col border rounded input-group date'>
                                    <input type='text' class="form-control" id='datetimepicker' style="border: none;"/>
                                    <span class="input-group-addon icon icon-calendar"
                                          style="margin-top: 1%; margin-left: 4%">
                                    </span>
                                </div>
                                <div class="col">
                                    <div class=' border rounded input-group date'>
                                        <input type='text' class="form-control" id='datetimepicker1'
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
                                    <div class="find-btn">
                                        <a class="btn btn-light-forlend btn-xl"><fmt:message key="tickets.searc"/></a>
                                    </div>
                                </div>
                                <div class="col"></div>
                                <div class="w-100" style="margin-top: 30px"></div>
                            </div>
                        </div>
                    </div>

                </div>
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
                        <c:forEach items="${flights}" var="flight">
                            <tr  data-toggle="modal"
                                 data-target="#ModalSeat" class="cursor">
                                <th scope="row">${flight.trainName}</th>
                                <td>${flight.arrivalStation} / ${flight.departureStation}
                                </td>
                                <td>${flight.arrivalTime} / ${flight.departureTime}</td>
                                <td>${flight.freeSeatNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
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
                        <select class="col selectpicker  border rounded" id="wagon" data-live-search="true">
                            <option>Место1</option>
                            <option>Место2</option>
                            <option>Место3</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="seat"><fmt:message key="tickets.modal.choose-seat"/></label>
                        <select class="col selectpicker  border rounded" id="seat" data-live-search="true">
                            <option>Место1</option>
                            <option>Место2</option>
                            <option>Место3</option>
                        </select>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit"><fmt:message key="tickets.modal.order"/></button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        <fmt:message key="tickets.modal.сancel"/></a>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
<%@ include file="../blocks/toast.jsp" %>
<%@ include file="../blocks/registration-modal.jsp" %>
<%@ include file="../blocks/login-modal.jsp" %>
<%@ include file="../blocks/scripts.jsp" %>
</body >
</html>
