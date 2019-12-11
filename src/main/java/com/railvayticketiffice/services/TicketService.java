package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCTicketDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TicketsDao;
import com.railvayticketiffice.dto.FlightDto;
import com.railvayticketiffice.dto.TicketDto;
import com.railvayticketiffice.entity.*;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.web.form.request.OrderForm;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static final Logger LOG = Logger.getLogger(TicketService.class);

    private CrudGenericDao<Ticket, Integer> ticketCrudDao;
    private TicketsDao ticketsDao;
    private UserService userService;


    public TicketService() {
        this.ticketCrudDao = DaoFactory.getEntityDao(DaoType.TICKET);
        this.ticketsDao = new JDBCTicketDao();
        this.userService = ServiceFactory.getUserService();
    }

    public List<Ticket> getFlightTickets(int flightId){
        List<Ticket> tickets = null;
        try {
            tickets = ticketsDao.getFlightTickets(flightId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return tickets;
    }

    public boolean addTicket(OrderForm form){
        Flight flight = new FlightService().getByPK(form.getFlightId());
        if(flight == null){
            return false;
        }
        Ticket ticket = new Ticket(form.getFlightId(), form.getUserId(), flight.getCost(), form.getSeatId());
        User user = userService.getByPK(form.getUserId());

        boolean success = false;
        try {
            success = ticketsDao.ticketsTransaction(ticket, user);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return success;
    }

    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = null;
        try {
            tickets = ticketCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return tickets;
    }

    public List<TicketDto> getUserTickets(int userId){
        List<Ticket> userAllTickets = getAllTickets().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
        List<TicketDto> userAllTicketsDto = new ArrayList<>();
        List<FlightDto> flightDtos = new FlightService().getAllDto();
        List<Seat> seats = new SeatService().getAll();
        List<Wagon> wagons = new WagonService().getAll();
        for(Ticket ticket : userAllTickets){
            FlightDto flightDto = flightDtos.stream().filter(x-> x.getId() == ticket.getFlightId()).findFirst().get();
            Seat seat = seats.stream().filter(x -> x.getId() == ticket.getSeatId()).findFirst().get();
            Wagon wagon = wagons.stream().filter(x -> x.getId() == seat.getWagonId()).findFirst().get();
            TicketDto ticketDto = new TicketDto( ticket.getId(), ticket.getFlightId(), ticket.getUserId(),
                    ticket.getCost(), ticket.getSeatId(), flightDto.getDepartureStation(), flightDto.getArrivalStation(),
                    flightDto.getTrainName(),  wagon.getName(), seat.getPlaceNumber(), flightDto.getDepartureTime() , flightDto.getArrivalTime());
            userAllTicketsDto.add(ticketDto);
        }
        return userAllTicketsDto;
    }

    public List<TicketDto> getActualUserTickets(int userId){
        LocalDateTime now = LocalDateTime.now();
        List<TicketDto> actualTickets = getUserTickets(userId).stream().filter(x -> x.getDepartureTime().isAfter(now)).collect(Collectors.toList());
        return getUserTickets(userId).stream().filter(x -> x.getDepartureTime().isAfter(now)).collect(Collectors.toList());

    }

    public List<TicketDto> getDeprecatedUserTickets(int userId){
        LocalDateTime now = LocalDateTime.now();
        return getUserTickets(userId).stream().filter(x -> x.getDepartureTime().isBefore(now)).collect(Collectors.toList());

    }

}
