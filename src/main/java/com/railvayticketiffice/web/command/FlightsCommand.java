package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddFlightForm;
import com.railvayticketiffice.web.form.request.FlightSearchForm;
import com.railvayticketiffice.web.form.validator.AddFlightFormValidator;
import com.railvayticketiffice.web.form.validator.FlightSearchFormValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_FLIGHTS_PAGE;
import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class FlightsCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(FlightsCommand.class);

    public static final String FLIGHTS_ATTRIBUTE = "flights";
    private static Gson gson = new Gson();


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

        AddFlightForm addFlightForm = getAddFlightForm(request);
        AjaxResponse ajaxResponse = new AjaxResponse();

        if(isAddFlightFormNotValid(addFlightForm)){
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_FLIGHTS_PAGE);
            ajaxResponse.setMessage("invalid form");
            return new Page(true, gson.toJson(ajaxResponse));
        }

        if(flightService.addNewFlight(addFlightForm)){
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl(ADMIN_FLIGHTS_PAGE);
            ajaxResponse.setMessage("flight added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
        else{
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_FLIGHTS_PAGE);
            ajaxResponse.setMessage("flight dont added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
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

    private boolean isFlightSearchFormNotValid(FlightSearchForm flightSearchForm) {
        return !validateForm(flightSearchForm, new FlightSearchFormValidator());
    }

    private AddFlightForm getAddFlightForm(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy'T'HH:mm");
        LocalDateTime departureDate = LocalDateTime.parse(request.getParameter("departure_date").trim(), formatter);
        LocalDateTime arrivalDate = LocalDateTime.parse(request.getParameter("arrival_date").trim(), formatter);
        return mapForm(request,
                req -> new AddFlightForm(
                        Integer.parseInt(request.getParameter("departure_station_id")),
                        Integer.parseInt(request.getParameter("arrival_station_id")),
                        departureDate,
                        arrivalDate,
                        Integer.parseInt(request.getParameter("train_id")),
                        Double.parseDouble(request.getParameter("cost"))
                        ));
    }

    private boolean isAddFlightFormNotValid(AddFlightForm addFlightForm) {
        return !validateForm(addFlightForm, new AddFlightFormValidator());
    }

}
