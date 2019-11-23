package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_FLIGHTS_PAGE;

public class AdminFlightsPageCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(ADMIN_FLIGHTS_PAGE);
    }

}
