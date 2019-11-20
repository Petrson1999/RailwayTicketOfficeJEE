package com.vladimirpetrenkodev.railvayticketiffice.web.command;

import com.vladimirpetrenkodev.railvayticketiffice.entity.User;
import com.vladimirpetrenkodev.railvayticketiffice.factory.ServiceFactory;
import com.vladimirpetrenkodev.railvayticketiffice.services.LoginService;
import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class LoginCommand extends MultipleMethodCommand implements Command {

    private LoginService userService;

    public LoginCommand() {
        this.userService = ServiceFactory.getLoginService();
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
        if (user!=null) {
            session.setAttribute("user", user);
            return new Page(PROFILE_PAGE, true);
        }

        session.setAttribute("error", "Error");
        return new Page("/", true);

    }
}
