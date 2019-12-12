package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.WagonService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.AddWagonTypeForm;
import com.railvayticketiffice.web.form.validator.AddWagonTypeFormValidator;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.ADMIN_WAGONS_PAGE;

public class WagonTypeCommand extends MultipleMethodCommand {
    private static Gson gson = new Gson();
    private WagonService wagonService;

    public WagonTypeCommand(){
        this.wagonService = ServiceFactory.getWagonService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        AddWagonTypeForm addWagonTypeForm = getAddWagonTypeForm(request);

        AjaxResponse ajaxResponse = new AjaxResponse();
        if(isFormNotValid(addWagonTypeForm)){
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("invalid form");
            return new Page(true, gson.toJson(ajaxResponse));
        }


        if(wagonService.addWagonType(addWagonTypeForm)){
            ajaxResponse.setSuccess(true);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("wagon type added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
        else{
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(ADMIN_WAGONS_PAGE);
            ajaxResponse.setMessage("wagon type dont added");
            return new Page(true, gson.toJson(ajaxResponse));
        }
    }

    private boolean isFormNotValid(AddWagonTypeForm addWagonTypeForm) {
        return !validateForm(addWagonTypeForm , new AddWagonTypeFormValidator());
    }

    private AddWagonTypeForm getAddWagonTypeForm(HttpServletRequest request) {
        return mapForm(request,
                req -> new AddWagonTypeForm(request.getParameter("type-name"),
                Integer.parseInt(request.getParameter("seat-count"))));
    }
}
