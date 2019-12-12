package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddTrainForm;

public class AddTrainFormValidator implements FormValidator<AddTrainForm>{
    @Override
    public boolean validate(AddTrainForm form) {
        if(form == null){
            return false;
        }
        if(form.getTrainName()== null){
            return false;
        }
        return form.getTrainName().length() != 0;
    }
}
