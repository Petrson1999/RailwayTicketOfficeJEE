package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.FlightSearchForm;

import java.time.LocalDateTime;

public class FlightSearchFormValidator implements FormValidator<FlightSearchForm> {
    @Override
    public boolean validate(FlightSearchForm form) {
        return validateStationsId(form) && validateDate(form);
    }

    private boolean validateStationsId(FlightSearchForm form) {
        boolean valid = true;
        if (form.getArrivalStationId() == form.getDepartureStationId()) {
            valid = false;
        }
        return valid;
    }

    private boolean validateDate(FlightSearchForm form) {
        boolean valid = true;
        if (form.getDateTime() == null) {
            valid = false;
        }
        LocalDateTime now = LocalDateTime.now();
        if(form.getDateTime().isBefore(form.getDateTime())){
            valid = false;
        }
        if(form.getDateTime().isBefore(now)){
            valid = false;
        }
        return valid;
    }

}
