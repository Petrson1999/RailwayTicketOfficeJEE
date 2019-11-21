package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class ProfilePageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingPageCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PROFILE_PAGE);
    }
}
