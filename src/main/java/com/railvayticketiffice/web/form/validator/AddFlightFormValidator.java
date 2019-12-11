package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddFlightForm;

public class AddFlightFormValidator implements FormValidator<AddFlightForm> {
    @Override
    public boolean validate(AddFlightForm form) {
        if(form == null){
            return false;
        }
        return validateDate(form) && validateStations(form) && validateTrain(form);
    }

    private boolean validateDate(AddFlightForm form) {
        boolean valid = true;
        if (form.getDepartureDate() == null || form.getArrivalDate() == null) {
            valid = false;
            return valid;
        }
        if (form.getArrivalDate().isBefore(form.getDepartureDate())) {
            valid = false;
        }
        return valid;
    }

    private boolean validateStations(AddFlightForm form) {
        boolean valid = true;
        if (form.getDepartureStationId() == 0 || form.getArrivalStationId() == 0) {
            valid = false;
        }
        if (form.getDepartureStationId() == form.getArrivalStationId()) {
            valid = false;
        }
        return valid;
    }

    private boolean validateTrain(AddFlightForm form) {
        return form.getTrainId() != 0;
    }
}
