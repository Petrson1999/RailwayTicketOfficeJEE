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
<body>
<%@ include file="../blocks/header.jsp" %>
<div class="background-image"></div>

<section class="py-5">
    <div class="card flex-row"
         style="width: 80%; margin-left: auto; margin-right: auto; margin-top: 5%">
        <div class="card-body" style="padding: 0">

            <div class="container-fluid" style="padding: 0">
                <h5 class="card-title text-center" style="margin-top: 1%">Админимтрирование</h5>
                <hr style="margin-bottom: 0">
            </div>
            <div class="container-fluid" style="padding: 0">
                <div id="wrapper">

                    <!-- Sidebar -->
                    <ul class="sidebar navbar-nav rounded-left ">
                        <li class="nav-item active">
                            <a class="nav-link" href="flights">
                                <span>Рейсы</span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="stations">
                                <span>Станции</span></a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="trains">
                                <span>Поезда</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="locomotives">
                                <span>Локомотивы</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="wagons">
                                <span>Вагоны</span></a>
                        </li>

                    </ul>

                    <div id="content-wrapper">

                        <div class="container">
                            <div style="width: 20%; cursor: pointer; margin: 3% auto;">
                                <a class="btn btn-dark btn-xl" style="color: white" data-toggle="modal"
                                   data-target="#ModalFlight">Добавить рейс</a>
                            </div>
                        </div>
                        <div class="container">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">#Поезда</th>
                                    <th scope="col">Откуда / Куда</th>
                                    <th scope="col">Дата</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Киев /
                                        Одесса
                                    </td>
                                    <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                                </tr>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Киев /
                                        Одесса
                                    </td>
                                    <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                                </tr>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Киев /
                                        Одесса
                                    </td>
                                    <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                                </tr>
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
            <div class="card-body" style=" height: 40em">
                <h5 class="card-title text-center">Добавление рейса</h5>
                <form class="form-flight">

                    <div class="form-group">
                        <div class="container">
                            <div class="row">
                                <div class="container">
                                    <div class="row">
                                        <div class="col">Откуда :</div>
                                        <div class="col">Куда :</div>
                                        <div class="w-100" style="margin: 2%"></div>
                                        <select class="col selectpicker border rounded" data-live-search="true">
                                            <option class="bg-dark" style="color: white">Станция1</option>
                                            <option class="bg-dark" style="color: white">Станция2</option>
                                            <option class="bg-dark" style="color: white">Станция3</option>
                                        </select>
                                        <select class="col selectpicker border rounded " data-live-search="true">
                                            <option class="bg-dark" style="color: white">Станция1</option>
                                            <option class="bg-dark" style="color: white">Станция2</option>
                                            <option class="bg-dark" style="color: white">Станция3</option>
                                        </select>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class="col">Дата отправления :</div>
                                        <div class="col">Дата прибытия :</div>
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
                                        <div class="col">Время отправления :</div>
                                        <div class="col">Время прибытия :</div>
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
                                        <div class="col">Поезд :</div>
                                        <div class="w-100 " style="margin: 2%"></div>
                                        <div class=" col row" style="width: 100%">
                                            <select class="col selectpicker border rounded" data-live-search="true">
                                                <option class="bg-dark" style="color: white">Поезд1</option>
                                                <option class="bg-dark" style="color: white">Поезд2</option>
                                                <option class="bg-dark" style="color: white">Поезд3</option>
                                            </select>
                                        </div>


                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <button class="btn btn-lg btn-dark btn-block text-uppercase" type="submit" style="margin-top: 45px">
                        Добавить
                    </button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer; margin-top: 5%"
                       data-toggle="modal"
                       data-dismiss="modal" aria-label="Close">
                        Отменить</a>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../blocks/scripts.jsp" %>
</body>
</html>
