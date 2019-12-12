package com.railvayticketiffice.factory;

import com.railvayticketiffice.services.*;

public class ServiceFactory {

    public static UserService getUserService() {
        return new UserService();
    }

    public static PageService getPageService() {
        return new PageService();
    }

    public static FlightService getFlightService() {
        return new FlightService();
    }

    public static StationService getStationService() {
        return new StationService();
    }

    public static TrainService getTrainService() {
        return new TrainService();
    }

    public static WagonService getWagonService() {
        return new WagonService();
    }

    public static SeatService getSeatService() {
        return new SeatService();
    }

    public static TicketService getTicketService() {
        return new TicketService();
    }

}
