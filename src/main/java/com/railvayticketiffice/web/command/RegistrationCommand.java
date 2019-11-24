package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.enums.Role;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.UserService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.RegistrationForm;
import com.railvayticketiffice.web.form.validator.RegistrationFormValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class RegistrationCommand extends MultipleMethodCommand{
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    private UserService userService;
    private static Gson gson = new Gson();

    public RegistrationCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return new Page("/" , true);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String login = request.getParameter("login");

        boolean exist = userService.isExist(login);

        LOG.info(String.format("User with login %s already exist %b", login, exist));

        if (exist) {
            return processError(request, "User already exist");
        }

        RegistrationForm registrationForm = getRegistrationForm(request);

        if (isFormNotValid(registrationForm)) {
            LOG.info("Registration form is invalid");
            return processError(request, "Invalid form");
        }

        User user = userService.registerUser(registrationForm);

        if (user!=null) {
            LOG.info("Redirect to home user.");
            request.getSession().setAttribute("user", user);
            return new Page("/" + PROFILE_PAGE, true);
        }

        return processError(request, "Could not create user");
    }

    private Page processError(HttpServletRequest request, String s) {
        AjaxResponse ajaxResponse = new AjaxResponse();
            LOG.info("Login success");
            ajaxResponse.setSuccess(false);
            ajaxResponse.setMessage(s);
            return new Page(true, gson.toJson(ajaxResponse));
    }

    private boolean isFormNotValid(RegistrationForm registrationForm) {
        return !RegistrationFormValidator.validate(registrationForm);
    }

    private RegistrationForm getRegistrationForm(HttpServletRequest request) {
        return mapForm(request,
                req -> new RegistrationForm(request.getParameter("first_name"),
                        request.getParameter("last_name"),
                        request.getParameter("login"),
                        request.getParameter("password"),
                        request.getParameter("password_confirm"),
                        Role.USER));
    }


}
