package com.railvayticketiffice.web.command.pages;

import com.railvayticketiffice.dto.WagonDto;
import com.railvayticketiffice.entity.WagonType;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.WagonService;
import com.railvayticketiffice.web.command.Command;
import com.railvayticketiffice.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_WAGONS_PAGE;

public class AdminWagonsPageCommand implements Command {
    public static final String WAGON_TYPES_ATTRIBUTE = "wagon_types";
    public static final String WAGONS_ATTRIBUTE = "wagons";

    private WagonService wagonService;

    public AdminWagonsPageCommand(){
        this.wagonService = ServiceFactory.getWagonService();
    }

    @Override
    public Page perform(HttpServletRequest request) {

        List<WagonType> wagonTypes = wagonService.getAllWagonTypes();

        request.setAttribute(WAGON_TYPES_ATTRIBUTE, wagonTypes);

        List<WagonDto> wagonDtos = wagonService.getAllWagonDto();

        request.setAttribute(WAGONS_ATTRIBUTE, wagonDtos);


        return new Page(ADMIN_WAGONS_PAGE);
    }
}
