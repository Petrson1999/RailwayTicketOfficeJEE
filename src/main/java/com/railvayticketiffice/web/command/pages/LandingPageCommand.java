package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.constant.PageUrlConstants;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;


public class LandingPageCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PageUrlConstants.LANDING_PAGE);
    }

}
