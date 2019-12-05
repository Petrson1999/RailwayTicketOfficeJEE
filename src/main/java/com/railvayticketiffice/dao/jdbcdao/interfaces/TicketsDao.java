package com.railvayticketiffice.dao.jdbcdao.interfaces;

import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.exeptions.PersistException;

import java.text.ParseException;
import java.util.List;

public interface TicketsDao {
    public List<Ticket> getFlightTickets(int flightId) throws PersistException;
}