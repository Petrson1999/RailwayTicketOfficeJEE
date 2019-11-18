package com.vladimirpetrenkodev.railvayticketiffice.web.command;

import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.PageUrlConstants.ADMIN_PAGE;

public class AdminCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(ADMIN_PAGE);
    }

}
