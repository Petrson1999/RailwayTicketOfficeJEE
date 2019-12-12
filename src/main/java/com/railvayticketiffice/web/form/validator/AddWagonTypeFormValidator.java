package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddWagonTypeForm;

public class AddWagonTypeFormValidator implements FormValidator<AddWagonTypeForm>{

    @Override
    public boolean validate(AddWagonTypeForm form) {
        if(form == null){
            return false;
        }
        return validateName(form) && validateSeatCount(form);
    }

    private boolean validateName(AddWagonTypeForm form){
        return form.getName() != null;
    }

    private boolean validateSeatCount(AddWagonTypeForm form){
        return form.getSeatsCount() != 0;
    }
}
