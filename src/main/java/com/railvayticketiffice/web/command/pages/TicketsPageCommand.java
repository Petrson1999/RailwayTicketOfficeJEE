package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.StationService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class TicketsPageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LandingPageCommand.class);
    private static final String STATIONS_ATTRIBUTE = "stations";

    private StationService stationService;

    public TicketsPageCommand(){
        this.stationService = ServiceFactory.getStationService();
    }

    @Override
    public Page perform(HttpServletRequest request) {

        request.getSession().setAttribute(STATIONS_ATTRIBUTE, stationService.getAll());

        return new Page(TICKETS_PAGE);
    }

}
