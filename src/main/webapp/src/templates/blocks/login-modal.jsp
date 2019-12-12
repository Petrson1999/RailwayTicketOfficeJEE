<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal login-->
<div class="modal fade" id="ModalLogin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">

        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <h5 class="card-title text-center"><fmt:message key="modal-login-title"/></h5>
                <form class="form-signin" id="form-sign-in" method="post">
                    <div class="form-label-group">
                        <input type="email" name="login" id="login" class="form-control" placeholder="<fmt:message key="modal-email"/>"
                               required autofocus>
                        <label for="login"><fmt:message key="modal-email"/></label>
                    </div>

                    <div class="form-label-group">
                        <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message key="modal-password"/>"
                               required>
                        <label for="password"><fmt:message key="modal-password"/></label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"><fmt:message key="modal-login-title-upper"/></button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-target="#ModalRegistration"
                       data-dismiss="modal" aria-label="Close">
                        <fmt:message key="modal-registration-title"/></a>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>
