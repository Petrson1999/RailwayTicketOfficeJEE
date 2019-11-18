package com.vladimirpetrenkodev.railvayticketiffice.web.command;

import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class ProfileCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingCommand.class);

    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(PROFILE_PAGE);
    }
}
