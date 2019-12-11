package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.dto.SeatsDto;
import com.railvayticketiffice.dto.WagonDto;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.web.form.request.FlightSearchForm;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

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

    public Flight getByPK(int flightId){
        Flight flight = null;
        try {
            flight = flightDao.getByPK(flightId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return flight;
    }

    public List<FlightDto> getAllDto() {
        List<Flight> flights = getAll();
        if (flights == null) {
            return null;
        }

        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightDto = new FlightDto(
                    flight.getId(),
                    flight.getDepartureStationId(),
                    flight.getArrivalStationId(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getCost(),
                    flight.getName(),
                    flight.getTrainId(),
                    stationService.getStationName(flight.getDepartureStationId()),
                    stationService.getStationName(flight.getArrivalStationId()),
                    trainService.getTrainName(flight.getTrainId()),
                    getAllFreeSeatsNumber(flight.getId())
            );
            flightDtos.add(flightDto);
        }
        return flightDtos;

    }

    private SeatsDto getFreeSeats(int flightId) {
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
            List<Seat> wagonFreeSeats = null;
            if (wagonAllSeats != null) {
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
        if (wagonSeat != null) {
            for (Seat seat : wagonSeat) {
                if (busyTickets.stream().noneMatch(x -> x.getSeatId() == seat.getId()) && freeWagonSeat.stream().noneMatch(x -> x.equals(seat))) {
                    freeWagonSeat.add(seat);
                }
            }
            return freeWagonSeat;
        } else return null;
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

    public List<WagonDto> getFlightWagonDto(int flightId) {
        List<WagonDto> wagonDtos = new ArrayList<>();

        Flight flight = getByPk(flightId);

        if (flight == null) {
            return null;
        }

        List<Wagon> wagons = wagonService.getTrainWagons(flight.getTrainId());
        if (wagons == null) {
            return null;
        }

        List<Ticket> tickets = ticketService.getFlightTickets(flight.getId());

        for (Wagon wagon : wagons) {
            List<Seat> wagonAllSeats = seatService.getWagonSeats(wagon.getId());
            List<Seat> wagonFreeSeats = null;
            if (wagonAllSeats != null) {
                wagonFreeSeats = getFreeSeatsInWagon(tickets, wagonAllSeats);
            }
            if (wagonFreeSeats != null) {
                wagonDtos.add(new WagonDto(wagon, wagonService.getWagonType(wagon.getWagonTypeId()), wagonFreeSeats));
            }
        }

        return wagonDtos;

    }

    public List<FlightDto> getFlightsBySearch(FlightSearchForm flightSearchForm) {
        List<FlightDto> flightDtos = getAllDto();
        if (flightDtos == null) {
            return null;
        }
        flightDtos = flightDtos.stream().filter(x -> x.getDepartureStationId() == flightSearchForm.getDepartureStationId()
                && x.getArrivalStationId() == flightSearchForm.getArrivalStationId()).collect(Collectors.toList());



        flightDtos = flightDtos.stream().filter(x ->
                x.getDepartureTime().getYear() == flightSearchForm.getDateTime().getYear() &&
                x.getDepartureTime().getMonth() == flightSearchForm.getDateTime().getMonth() &&
                x.getDepartureTime().getDayOfMonth() == flightSearchForm.getDateTime().getDayOfMonth() &&
                x.getDepartureTime().getHour() >= flightSearchForm.getDateTime().getHour() &&
                x.getDepartureTime().getMinute() >= flightSearchForm.getDateTime().getMinute()
                ).collect(Collectors.toList());

        return flightDtos;
    }



}

