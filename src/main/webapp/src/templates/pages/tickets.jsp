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
                <h5 class="card-title text-center">Заказ билетов</h5>
                <hr>
                <div class="container">
                    <div class="row">
                        <div class="container">
                            <div class="row">
                                <div class="col">Откуда</div>
                                <div class="col"></div>
                                <div class="col">Куда</div>
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
                                <div class="col">Дата отправления</div>
                                <div class="col">Время отправления с:</div>
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
                                        <a class="btn btn-light-forlend btn-xl">Поиск поездов</a>
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
                        <th scope="col">#Поезда</th>
                        <th scope="col">Откуда / Куда</th>
                        <th scope="col">Дата</th>
                        <th scope="col">Свободных мест</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr  data-toggle="modal"
                             data-target="#ModalSeat" class="cursor">
                            <th scope="row">1</th>
                            <td>Киев /
                                Одесса
                            </td>
                            <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                            <td>25</td>
                        </tr>
                        <tr data-toggle="modal"
                            data-target="#ModalSeat" class="cursor">
                            <th scope="row">1</th>
                            <td>Киев /
                                Одесса
                            </td>
                            <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                            <td>25</td>
                        </tr>
                        <tr data-toggle="modal"
                            data-target="#ModalSeat"class="cursor" >
                            <th scope="row">1</th>
                            <td>Киев /
                                Одесса
                            </td>
                            <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                            <td>25</td>
                        </tr>
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
                <h5 class="card-title text-center">Заказ билета</h5>
                <form class="form-seat">

                    <div class="form-group">
                        <label for="seat">Выберите место: </label>
                        <select class="col selectpicker  border rounded" id="seat" data-live-search="true">
                            <option>Место1</option>
                            <option>Место2</option>
                            <option>Место3</option>
                        </select>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit">Заказать</button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        Отменить</a>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
<%@ include file="../blocks/scripts.jsp" %>
</body >
</html>
