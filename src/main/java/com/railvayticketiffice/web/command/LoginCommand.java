package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class LoginCommand extends MultipleMethodCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);


    private UserService userService;
    private static Gson gson = new Gson();


    public LoginCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        User user = userService.validateUser(login, password);

        AjaxResponse ajaxResponse = new AjaxResponse();
        if (user != null) {
            LOG.info("Login success");
            session.setAttribute("user", user);
            ajaxResponse.setUrl(PROFILE_PAGE);
            ajaxResponse.setSuccess(true);
            return new Page(true, gson.toJson(ajaxResponse));
        }

        ajaxResponse.setMessage("Invalid credential! Please, try again.");
        return new Page(true, gson.toJson(ajaxResponse));
    }
}
