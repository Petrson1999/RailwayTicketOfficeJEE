package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.StationService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddStationForm;
import com.railvayticketiffice.web.form.validator.AddStationFormValidator;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_STATIONS_PAGE;

public class StationCommand extends MultipleMethodCommand {
    private static Gson gson = new Gson();
    private StationService stationService;

    public StationCommand(){
        this.stationService = ServiceFactory.getStationService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        AddStationForm addStationForm = getAddStationForm(request);

        AjaxResponse ajaxResponse = new AjaxResponse();
        if(isFormNotValid(addStationForm)){
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_STATIONS_PAGE);
            ajaxResponse.setMessage("invalid form");
            return new Page(true, gson.toJson(ajaxResponse));
        }


        if(stationService.addStation(addStationForm)){
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl(ADMIN_STATIONS_PAGE);
            ajaxResponse.setMessage("station added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
        else{
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_STATIONS_PAGE);
            ajaxResponse.setMessage("station dont added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
    }

    private boolean isFormNotValid(AddStationForm addStationForm) {
        return !validateForm(addStationForm , new AddStationFormValidator());
    }

    private AddStationForm getAddStationForm(HttpServletRequest request) {
        return mapForm(request,
                req -> new AddStationForm(request.getParameter("station_name")));
    }
}
