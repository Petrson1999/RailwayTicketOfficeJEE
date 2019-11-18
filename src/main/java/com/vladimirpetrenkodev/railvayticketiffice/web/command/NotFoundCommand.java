package com.vladimirpetrenkodev.railvayticketiffice.web.command;


import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import static com.vladimirpetrenkodev.railvayticketiffice.constant.PageUrlConstants.NOT_FOUND_PAGE;


public class NotFoundCommand implements Command {

    @Override
    public Page perform(HttpServletRequest request){
        return new Page(NOT_FOUND_PAGE);
    }
}
