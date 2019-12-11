package com.railvayticketiffice.web.command;

import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    
    Page perform(HttpServletRequest request);
}
