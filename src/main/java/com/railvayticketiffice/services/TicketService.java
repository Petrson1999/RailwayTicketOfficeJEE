package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCTicketDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TicketsDao;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.web.form.request.OrderForm;
import org.apache.log4j.Logger;

import java.util.List;

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

}
