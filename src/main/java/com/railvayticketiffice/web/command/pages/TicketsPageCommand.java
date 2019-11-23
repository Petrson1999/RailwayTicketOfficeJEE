package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class TicketsPageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingPageCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(TICKETS_PAGE);
    }

}
