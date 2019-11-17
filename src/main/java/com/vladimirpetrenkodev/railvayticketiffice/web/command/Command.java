package com.vladimirpetrenkodev.railvayticketiffice.web.command;

import com.vladimirpetrenkodev.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    
    Page perform(HttpServletRequest request);
}
