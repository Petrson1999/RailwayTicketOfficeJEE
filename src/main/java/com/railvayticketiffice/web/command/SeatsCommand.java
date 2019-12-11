package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.dto.WagonDto;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.response.WagonResponse;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class SeatsCommand implements Command {
    private static final String FLIGHTS_ID_PARAMETER = "flightsId";
    private static final String SEATS_ATTRIBUTE = "seats";


    private static final Logger LOG = Logger.getLogger(FlightService.class);
    private FlightService flightService;
    private static Gson gson = new Gson();


    public SeatsCommand() {
        this.flightService = ServiceFactory.getFlightService();
    }

    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        int flightId = Integer.parseInt(request.getParameter(FLIGHTS_ID_PARAMETER));
        List<WagonDto> wagonDtos = flightService.getFlightWagonDto(flightId);

        WagonResponse wagonResponse = new WagonResponse();
        if (wagonDtos != null) {
            LOG.info("Login success");
            session.setAttribute(SEATS_ATTRIBUTE, wagonDtos);
            wagonResponse.setUrl(TICKETS_PAGE);
            wagonResponse.setSuccess(true);
            wagonResponse.setWagonDtos(wagonDtos);
            return new Page(true, gson.toJson(wagonResponse));
        }

        wagonResponse.setMessage("No tickets!");
        return new Page(true, gson.toJson(wagonResponse));
    }

}
