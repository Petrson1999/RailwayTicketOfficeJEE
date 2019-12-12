package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddFlightForm;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AddFlightFormValidatorTest {

    private AddFlightFormValidator addFlightFormValidator;
    private AddFlightForm addFlightForm;

    @Before
    public void setUp(){
        addFlightFormValidator = new AddFlightFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        arrivalDate = arrivalDate.plusYears(1);
        addFlightForm = new AddFlightForm(1,2, LocalDateTime.now(), arrivalDate, 1, 10);
        boolean result = addFlightFormValidator.validate(addFlightForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfStationEqualsFormValid() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        arrivalDate = arrivalDate.plusYears(1);
        addFlightForm = new AddFlightForm(1,1, LocalDateTime.now(), arrivalDate, 1, 10);
        boolean result = addFlightFormValidator.validate(addFlightForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfDepartureAfterArrival() {
        LocalDateTime departureDate = LocalDateTime.now();
        departureDate = departureDate.plusYears(1);
        addFlightForm = new AddFlightForm(1,1, departureDate, LocalDateTime.now(), 1, 10);
        boolean result = addFlightFormValidator.validate(addFlightForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfTrainIdNull() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        arrivalDate = arrivalDate.plusYears(1);
        addFlightForm = new AddFlightForm(1,2, LocalDateTime.now(), arrivalDate, 0, 10);
        boolean result = addFlightFormValidator.validate(addFlightForm);
        assertFalse(result);
    }

}