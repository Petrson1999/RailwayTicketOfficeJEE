package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.TrainService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_TRAINS_PAGE;

public class AdminTrainCommad implements Command {

    private static final String TRAINS_ATTRIBUTE = "trains";
    private TrainService trainService;

    public AdminTrainCommad(){
        this.trainService = ServiceFactory.getTrainService();
    }


    @Override
    public Page perform(HttpServletRequest request) {

        List<Train> trains = trainService.getAllTrains();

        request.getSession().setAttribute(TRAINS_ATTRIBUTE, trains);
        return new Page(ADMIN_TRAINS_PAGE);
    }
}
