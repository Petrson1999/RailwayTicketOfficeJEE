package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.OrderForm;

public class OrderFormValidator implements FormValidator<OrderForm>{

    @Override
    public boolean validate(OrderForm form) {
        if(form == null){
            return false;
        }
        return (form.getFlightId() > 0) && (form.getSeatId() > 0) && (form.getWagonId() > 0);
    }
}
