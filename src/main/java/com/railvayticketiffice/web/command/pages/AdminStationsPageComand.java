package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.StationService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddStationForm;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_STATIONS_PAGE;

public class AdminStationsPageComand implements Command {
    private static final String STATIONS_ATTRIBUTE = "stations";
    private StationService stationService;

    public AdminStationsPageComand(){
        this.stationService = ServiceFactory.getStationService();
    }


    @Override
    public Page perform(HttpServletRequest request) {

        List<Station> stations = stationService.getAll();

        request.getSession().setAttribute(STATIONS_ATTRIBUTE, stations);
        return new Page(ADMIN_STATIONS_PAGE);
    }


}
