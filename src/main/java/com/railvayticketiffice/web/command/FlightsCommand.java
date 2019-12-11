package com.railvayticketiffice.web.command;

import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.FlightSearchForm;
import com.railvayticketiffice.web.form.validator.FlightSearchFormValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class FlightsCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(FlightsCommand.class);

    public static final String FLIGHTS_ATTRIBUTE = "flights";

    private FlightService flightService;


    public FlightsCommand() {
        this.flightService = ServiceFactory.getFlightService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {


        FlightSearchForm flightSearchForm = getFlightSearchForm(request);

        if (isFlightSearchFormNotValid(flightSearchForm)) {
            LOG.info("flight search form is invalid");
            return new Page(TICKETS_PAGE);
        }


        List<FlightDto> flightDtos = flightService.getFlightsBySearch(flightSearchForm);
        if (flightDtos != null && flightDtos.size() != 0) {
            request.setAttribute(FLIGHTS_ATTRIBUTE, flightDtos);
            return new Page(TICKETS_PAGE);
        }

        request.setAttribute("message", "nonFlight");
        return new Page(TICKETS_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        return new Page(TICKETS_PAGE);
    }

    private FlightSearchForm getFlightSearchForm(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"), formatter);
        return mapForm(request,
                req -> new FlightSearchForm(
                        Integer.parseInt(request.getParameter("departureStationId")),
                        Integer.parseInt(request.getParameter("arrivalStationId")),
                        dateTime));
    }

    private boolean isFlightSearchFormNotValid(FlightSearchForm registrationForm) {
        return !validateForm(registrationForm, new FlightSearchFormValidator());
    }


}
