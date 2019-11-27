package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class TicketsPageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingPageCommand.class);
    private static final String FLIGHTS_ATTRIBUTE = "flights";

    private FlightService flightService;



    public TicketsPageCommand(){
        this.flightService = ServiceFactory.getFlightService();
    }

    @Override
    public Page perform(HttpServletRequest request) {


        request.setAttribute(FLIGHTS_ATTRIBUTE, flightService.getAllDto());


        return new Page(TICKETS_PAGE);
    }

}
