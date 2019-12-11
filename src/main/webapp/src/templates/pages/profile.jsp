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
            <h5 class="card-title text-center"><fmt:message key="profile.profile"/></h5>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><fmt:message key="profile.login"/> : ${user.login}</li>
                <li class="list-group-item"><fmt:message key="modal-registration-name"/> : ${user.name}</li>
                <li class="list-group-item"><fmt:message key="modal-registration-surname"/> : ${user.surname}</li>
            </ul>
            <h5 class="card-title text-center profile-card-header"><fmt:message key="profile.my-tickets"/></h5>
            <div class="container">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#<fmt:message key="tickets.train"/></th>
                        <th scope="col"><fmt:message key="tickets.from-where-to-where"/></th>
                        <th scope="col"><fmt:message key="tickets.date"/></th>
                        <th scope="col"><fmt:message key="profile.wagon"/></th>
                        <th scope="col"><fmt:message key="profile.seat"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.actual_tickets}" var="actual_ticket">
                    <tr>
                        <td>${actual_ticket.trainName}</td>
                        <td>${actual_ticket.departureStation} / ${actual_ticket.arrivalStation}</td>
                        <td> <span class="badge badge-success">${actual_ticket.formatedDepartureTime} / ${actual_ticket.formatedArrivalTime}</span></td>
                        <td>${actual_ticket.wagonNumber}</td>
                        <td>${actual_ticket.seatNumber}</td>
                    </tr>
                    </c:forEach>
                    <c:forEach items="${requestScope.deprecated_tickets}" var="deprecated_ticket">
                    <tr>
                        <td>${deprecated_ticket.trainName}</td>
                        <td>${deprecated_ticket.departureStation} / ${deprecated_ticket.arrivalStation}</td>
                        <td> <span class="badge badge-danger">${deprecated_ticket.formatedDepartureTime} / ${deprecated_ticket.formatedArrivalTime}</span></td>
                        <td>${deprecated_ticket.wagonNumber}</td>
                        <td>${deprecated_ticket.seatNumber}</td>
                    </c:forEach>
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
