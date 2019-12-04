package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.FlightSearchForm;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<FlightDto> flightDtos = flightService.getAllDto();
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (flightDtos != null) {
            request.setAttribute(FLIGHTS_ATTRIBUTE, flightDtos);
            return new Page(TICKETS_PAGE);
        }
        ajaxResponse.setMessage("No flights on a given path.");
        return new Page(TICKETS_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        return new Page(TICKETS_PAGE);
    }

    private FlightSearchForm getFlightSearchForm(HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        final Date date;
        Date d = null;
        try {
            d = format.parse(request.getParameter("date") + request.getParameter("time"));
        } catch (ParseException e) {
            LOG.error("error parse date");
        } finally {
            date = d;
        }

        return mapForm(request,
                req -> new FlightSearchForm(
                        Integer.parseInt(request.getParameter("departureStationId")),
                        Integer.parseInt(request.getParameter("arrivalStationId")),
                        date));
    }
}
