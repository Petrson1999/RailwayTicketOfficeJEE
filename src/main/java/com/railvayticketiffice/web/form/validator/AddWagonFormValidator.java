package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddWagonForm;

public class AddWagonFormValidator implements FormValidator<AddWagonForm>{
    @Override
    public boolean validate(AddWagonForm form) {
        return validateName(form) && validateTypeId(form);
    }

    private boolean validateName(AddWagonForm form){
        return form.getName() != null;
    }

    private boolean validateTypeId(AddWagonForm form){
        return form.getTypeId() != 0;
    }
}
