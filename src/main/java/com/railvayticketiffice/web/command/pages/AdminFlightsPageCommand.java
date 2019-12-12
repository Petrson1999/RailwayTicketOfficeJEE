package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.FlightService;
import com.railvayticketiffice.services.StationService;
import com.railvayticketiffice.services.TrainService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_FLIGHTS_PAGE;

public class AdminFlightsPageCommand implements Command {
    public static final String FLIGHTS_ATTRIBUTE = "flights";
    private static final String STATIONS_ATTRIBUTE = "stations";
    private static final String TRAINS_ATTRIBUTE = "trains";

    private StationService stationService;
    private FlightService flightService;
    private TrainService trainService;


    public AdminFlightsPageCommand() {
        this.flightService = ServiceFactory.getFlightService();
        this.stationService = ServiceFactory.getStationService();
        this.trainService = ServiceFactory.getTrainService();
    }

    @Override
    public Page perform(HttpServletRequest request) {

        List<Station> stations = stationService.getAll();

        request.getSession().setAttribute(STATIONS_ATTRIBUTE, stations);

        List<FlightDto> flightDtos = flightService.getAllDto();

        request.setAttribute(FLIGHTS_ATTRIBUTE, flightDtos);

        List<Train> trains = trainService.getAllTrains();

        request.getSession().setAttribute(TRAINS_ATTRIBUTE , trains);

        return new Page(ADMIN_FLIGHTS_PAGE);

    }

}
