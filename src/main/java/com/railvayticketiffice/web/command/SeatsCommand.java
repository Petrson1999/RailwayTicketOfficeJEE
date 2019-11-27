package com.railvayticketiffice.web.command;

import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class SeatsCommand implements Command  {
    private static final String FLIGHTS_ID_PARAMETER = "flightsId";
    private static final String SEATS_ATTRIBUTE = "seats";


    private static final Logger LOG = Logger.getLogger(FlightService.class);
    private FlightService flightService;

    public SeatsCommand(){
        this.flightService = ServiceFactory.getFlightService();
    }

    @Override
    public Page perform(HttpServletRequest request) {
        int flightId = Integer.parseInt(request.getParameter(FLIGHTS_ID_PARAMETER));

        request.setAttribute(SEATS_ATTRIBUTE, flightService.getFreeSeats(flightId));
        return new Page("/" + TICKETS_PAGE, true);
    }
}
