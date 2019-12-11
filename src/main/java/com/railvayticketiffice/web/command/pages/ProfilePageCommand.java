package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.dto.TicketDto;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.TicketService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.PROFILE_PAGE;

public class ProfilePageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingPageCommand.class);
    private TicketService ticketService;
    private static final String USER_ATTRIBUTE = "user";
    private static final String ACTUAL_TICKETS_ATTRIBUTE = "actual_tickets";
    private static final String DEPRECATED_TICKETS_ATTRIBUTE = "deprecated_tickets";

    public ProfilePageCommand(){
        this.ticketService = ServiceFactory.getTicketService();
    }

    @Override
    public Page perform(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);

        List<TicketDto> actualTickets = ticketService.getActualUserTickets(user.getId());

        List<TicketDto> depricatedTickets = ticketService.getDeprecatedUserTickets(user.getId());

        request.setAttribute(ACTUAL_TICKETS_ATTRIBUTE, actualTickets);

        request.setAttribute(DEPRECATED_TICKETS_ATTRIBUTE, depricatedTickets);

        return new Page(PROFILE_PAGE);
    }
}
