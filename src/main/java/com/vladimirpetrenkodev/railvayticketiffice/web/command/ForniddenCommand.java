package com.vladimirpetrenkodev.railvayticketiffice.web.command;

import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.PageUrlConstants.FORBIDDEN_PAGE;

public class ForniddenCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(FORBIDDEN_PAGE);
    }

}
