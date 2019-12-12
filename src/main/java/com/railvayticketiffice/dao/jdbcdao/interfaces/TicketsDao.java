package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.exeptions.PersistException;

import java.text.ParseException;
import java.util.List;

/**
 * Interface with methods for tickets data management
 *
 * @author Vladimir Petrenko
 */
public interface TicketsDao {
    /**
     * get all tickets by flight id
     *
     * @param flightId flight id
     *
     * @return list tickets on flight with id from param
     */
    public List<Ticket> getFlightTickets(int flightId) throws PersistException;
    /**
     * add new tickets through a transaction
     *
     * @param ticket new ticket
     * @param user ticket buyer
     *
     * @return is the operation successful
     */
    public boolean ticketsTransaction(Ticket ticket, User user) throws PersistException;
}
