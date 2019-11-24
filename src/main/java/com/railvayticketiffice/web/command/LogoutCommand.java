package com.railvayticketiffice.web.command;

import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LogoutCommand implements Command{
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    
    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOG.debug("Invalidate session with ID: " +  session.getId());
        
        session.invalidate();
        return new Page("/", true);
    }
}
