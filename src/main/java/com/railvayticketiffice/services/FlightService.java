package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.dto.SeatsDto;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.factory.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightService {
    private static final Logger LOG = Logger.getLogger(FlightService.class);

    private CrudGenericDao<Flight, Integer> flightDao;
    private StationService stationService;
    private TrainService trainService;
    private WagonService wagonService;
    private SeatService seatService;
    private TicketService ticketService;


    public FlightService() {
        this.flightDao = DaoFactory.getEntityDao(DaoType.FLIGHT);
        this.stationService = ServiceFactory.getStationService();
        this.trainService = ServiceFactory.getTrainService();
        this.wagonService = ServiceFactory.getWagonService();
        this.seatService = ServiceFactory.getSeatService();
        this.ticketService = ServiceFactory.getTicketService();
    }

    public List<Flight> getAll() {
        List<Flight> flights = null;
        try {
            flights = flightDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return flights;
    }

    public List<FlightDto> getAllDto() {
        List<Flight> flights = getAll();
        if (flights == null) {
            return null;
        }

        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightDto = new FlightDto();
            flightDto.setId(flight.getId());
            flightDto.setName(flight.getName());
            flightDto.setCost(flight.getCost());
            flightDto.setDepartureStation(stationService.getStationName(flight.getDepartureStationId()));
            flightDto.setArrivalStation(stationService.getStationName(flight.getArrivalStationId()));
            flightDto.setDepartureTime(flight.getDepartureTime());
            flightDto.setArrivalTime(flight.getArrivalTime());
            flightDto.setTrainName(trainService.getTrainName(flight.getTrainId()));
            flightDto.setFreeSeatNumber(getAllFreeSeatsNumber(flight.getId()));
            flightDtos.add(flightDto);
        }
        return flightDtos;

    }

    public SeatsDto getFreeSeats(int flightId) {
        Flight flight = getByPk(flightId);

        if (flight == null) {
            return null;
        }

        SeatsDto seatDto = new SeatsDto();

        List<Wagon> wagons = wagonService.getTrainWagons(flight.getTrainId());
        if (wagons == null) {
            return null;
        }

        List<Ticket> tickets = ticketService.getFlightTickets(flight.getId());

        Map<Wagon, List<Seat>> freeSeats = new HashMap<>();

        for (Wagon wagon : wagons) {
            List<Seat> wagonAllSeats = seatService.getWagonSeats(wagon.getId());
            List<Seat> wagonFreeSeats =null;
            if(wagonAllSeats!=null) {
                wagonFreeSeats = getFreeSeatsInWagon(tickets, wagonAllSeats);
            }
            if (wagonFreeSeats != null) {
                freeSeats.put(wagon, wagonFreeSeats);
            }
        }

        seatDto.setFreeSeats(freeSeats);

        return seatDto;
    }

    private List<Seat> getFreeSeatsInWagon(List<Ticket> busyTickets, List<Seat> wagonSeat) {
        List<Seat> freeWagonSeat = new ArrayList<>();
        if(wagonSeat!= null) {
            for (Seat seat : wagonSeat) {
                if (busyTickets.stream().noneMatch(x -> x.getSeatId() == seat.getId()) && freeWagonSeat.stream().noneMatch(x -> x.equals(seat))) {
                    freeWagonSeat.add(seat);
                }
            }
            return freeWagonSeat;
        }
        else return null;
    }

    private int getAllFreeSeatsNumber(int flightId) {
        int number = 0;
        SeatsDto seatsDto = getFreeSeats(flightId);
        for (Map.Entry<Wagon, List<Seat>> wagonSeats : seatsDto.getFreeSeats().entrySet()) {
            number += wagonSeats.getValue().size();
        }
        return number;
    }

    private Flight getByPk(int flightId) {
        Flight flight = null;
        try {
            flight = this.flightDao.getByPK(flightId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return flight;
    }
}

