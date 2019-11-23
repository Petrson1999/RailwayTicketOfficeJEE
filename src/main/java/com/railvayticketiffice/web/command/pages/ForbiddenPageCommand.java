package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.FORBIDDEN_PAGE;

public class ForbiddenPageCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(FORBIDDEN_PAGE);
    }

}
