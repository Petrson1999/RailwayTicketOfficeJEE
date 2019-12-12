package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddStationForm;

public class AddStationFormValidator implements FormValidator<AddStationForm> {

    @Override
    public boolean validate(AddStationForm form) {
        if(form == null){
            return false;
        }
        if(form.getStationName()== null){
            return false;
        }
        return form.getStationName().length() != 0;
    }
}
