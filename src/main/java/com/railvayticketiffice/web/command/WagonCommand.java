package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.WagonService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddWagonForm;
import com.railvayticketiffice.web.form.validator.AddWagonFormValidator;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_WAGONS_PAGE;

public class WagonCommand extends MultipleMethodCommand {
    private static Gson gson = new Gson();
    private WagonService wagonService;

    public WagonCommand(){
        this.wagonService = ServiceFactory.getWagonService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        AddWagonForm addWagonForm = getAddWagonTypeForm(request);

        AjaxResponse ajaxResponse = new AjaxResponse();
        if(isFormNotValid(addWagonForm)){
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("invalid form");
            return new Page(true, gson.toJson(ajaxResponse));
        }


        if(wagonService.addWagon(addWagonForm)){
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("wagon added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
        else{
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("wagon dont added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
    }

    private boolean isFormNotValid(AddWagonForm addWagonForm) {
        return !validateForm(addWagonForm , new AddWagonFormValidator());
    }

    private AddWagonForm getAddWagonTypeForm(HttpServletRequest request) {
        return mapForm(request,
                req -> new AddWagonForm(request.getParameter("wagon_name"),
                        Integer.parseInt(request.getParameter("type-id"))));
    }
}
