<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Railway ticket office profile</title>
    <base href="${pageContext.request.contextPath}/">
    <%@include file="../blocks/head.jsp"%>
</head>
<body class="body">
<%@ include file="../blocks/header.jsp" %>

<div class="background-image"></div>

<section class="py-5">
    <div class="card flex-row my-5 card-center">
        <div class="card-body">
            <h5 class="card-title text-center">Профиль</h5>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Логин : vladimir@gmail.com</li>
                <li class="list-group-item">Имя : Владимир</li>
                <li class="list-group-item">Фамилия : Петренко</li>
            </ul>
            <h5 class="card-title text-center profile-card-header">Мои билеты</h5>
            <div class="container">
                <div class="margin-right-10">
                    <input type="checkbox" class="custom-checkbox">Показывать прошедшие
                </div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#Поезда</th>
                        <th scope="col">Откуда / Куда</th>
                        <th scope="col">Дата</th>
                        <th scope="col">Вагон</th>
                        <th scope="col">Место</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Киев /
                            Одесса
                        </td>
                        <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                        <td>25</td>
                        <td>25</td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>Киев /
                            Одесса
                        </td>
                        <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                        <td>25</td>
                        <td>25</td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>Киев /
                            Одесса
                        </td>
                        <td>20-11-2019 12:00 / 20-11-2019 20:00</td>
                        <td>25</td>
                        <td>25</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<%@ include file="../blocks/toast.jsp" %>
<%@ include file="../blocks/scripts.jsp" %>
</body>
</html>
