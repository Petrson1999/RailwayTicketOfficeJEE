<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal registration-->
<div class="modal fade" id="ModalRegistration" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="card card-signin flex-row my-5 modal-content">
            <div class="card-body">
                <%@ include file="../blocks/toast.jsp" %>
                <h5 class="card-title text-center"><fmt:message key="modal-registration-title"/></h5>
                <form class="form-signin" id="form-registration" method="post">

                    <div class="form-label-group">
                        <input type="text" id="first-name" class="form-control" placeholder="<fmt:message key="modal-registration-name"/>"
                               required>
                        <label for="first-name"><fmt:message key="modal-registration-name"/></label>
                    </div>

                    <div class="form-label-group">
                        <input type="text" id="last-name" class="form-control" placeholder="<fmt:message key="modal-registration-surname"/>"
                               required>
                        <label for="last-name"><fmt:message key="modal-registration-surname"/></label>
                    </div>

                    <div class="form-label-group">
                        <input type="email" id="inputEmail" class="form-control" placeholder="<fmt:message key="modal-email"/>"
                               required>
                        <label for="inputEmail"><fmt:message key="modal-email"/></label>
                    </div>

                    <hr>

                    <div class="form-label-group">
                        <input type="password" id="inputPassword" class="form-control"
                               placeholder="<fmt:message key="modal-password"/>" required>
                        <label for="inputPassword"><fmt:message key="modal-password"/></label>
                    </div>

                    <div class="form-label-group">
                        <input type="password" id="inputConfirmPassword" class="form-control"
                               placeholder="<fmt:message key="modal-registration-confirm-password"/>" required>
                        <label for="inputConfirmPassword"><fmt:message key="modal-registration-confirm-password"/></label>
                    </div>

                    <button class="btn btn-lg  btn-primary btn-block text-uppercase" type="submit"><fmt:message key="modal-registration-title-upper"/>
                    </button>
                    <a class="d-block text-center mt-2 small" style="cursor: pointer" data-toggle="modal"
                       data-target="#ModalLogin"
                       data-dismiss="modal" aria-label="Close"><fmt:message key="modal-login-title"/></a>
                </form>

            </div>
        </div>
    </div>
</div>
</body>
</html>
