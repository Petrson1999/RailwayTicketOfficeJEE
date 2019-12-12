package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.FlightSearchForm;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FlightSearchFormValidatorTest {
    private FlightSearchFormValidator flightSearchFormValidator;
    private FlightSearchForm flightSearchForm;

    @Before
    public void setUp() {
        flightSearchFormValidator = new FlightSearchFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        int departureStationId = 1;
        int arrivalStationId = 2;
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusYears(1);
        flightSearchForm = new FlightSearchForm(departureStationId, arrivalStationId, localDateTime);
        boolean result = flightSearchFormValidator.validate(flightSearchForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfStationsIdsEquals() {
        int departureStationId = 1;
        int arrivalStationId = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusYears(1);
        flightSearchForm = new FlightSearchForm(departureStationId, arrivalStationId, localDateTime);
        boolean result = flightSearchFormValidator.validate(flightSearchForm);
        assertFalse(result);
    }
}