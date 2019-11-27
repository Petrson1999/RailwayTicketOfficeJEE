package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCTicketDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.TicketsDao;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class TicketService {
    private static final Logger LOG = Logger.getLogger(TicketService.class);

    private CrudGenericDao<Ticket, Integer> stationCrudDao;
    private TicketsDao ticketsDao;


    public TicketService() {
        this.stationCrudDao = DaoFactory.getEntityDao(DaoType.TICKET);
        this.ticketsDao = new JDBCTicketDao();
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
}
