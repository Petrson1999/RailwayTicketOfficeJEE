package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.TrainService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddTrainForm;
import com.railvayticketiffice.web.form.validator.AddTrainFormValidator;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_STATIONS_PAGE;
import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_TRAINS_PAGE;

public class TrainCommand extends MultipleMethodCommand {
    private static Gson gson = new Gson();
    private TrainService trainService;

    public TrainCommand(){
        this.trainService = ServiceFactory.getTrainService();
    }
    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        AddTrainForm addTrainForm = getAddTrainForm(request);

        AjaxResponse ajaxResponse = new AjaxResponse();
        if(isFormNotValid(addTrainForm)){
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_TRAINS_PAGE);
            ajaxResponse.setMessage("invalid form");
            return new Page(true, gson.toJson(ajaxResponse));
        }


        if(trainService.addTrain(addTrainForm)){
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl(ADMIN_TRAINS_PAGE);
            ajaxResponse.setMessage("train added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
        else{
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_TRAINS_PAGE);
            ajaxResponse.setMessage("train dont added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
    }

    private boolean isFormNotValid(AddTrainForm addTrainForm) {
        return !validateForm(addTrainForm , new AddTrainFormValidator());
    }

    private AddTrainForm getAddTrainForm(HttpServletRequest request) {
        return mapForm(request,
                req -> new AddTrainForm(request.getParameter("train_name")));
    }
}
