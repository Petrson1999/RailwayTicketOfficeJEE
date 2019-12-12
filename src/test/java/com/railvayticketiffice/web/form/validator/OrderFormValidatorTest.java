package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.OrderForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderFormValidatorTest {
    private OrderFormValidator orderFormValidator;
    private OrderForm orderForm;

    @Before
    public void setUp() {
        orderFormValidator = new OrderFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        int flightId = 1;
        int wagonId = 1;
        int seatId = 1;
        int userId = 1;
        orderForm = new OrderForm(flightId, wagonId, seatId, userId);
        boolean result = orderFormValidator.validate(orderForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfFlightIdInvalid() {
        int flightId = 0;
        int wagonId = 1;
        int seatId = 1;
        int userId = 1;
        orderForm = new OrderForm(flightId, wagonId, seatId, userId);
        boolean result = orderFormValidator.validate(orderForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfWagonIdInvalid() {
        int flightId = 1;
        int wagonId = 0;
        int seatId = 1;
        int userId = 1;
        orderForm = new OrderForm(flightId, wagonId, seatId, userId);
        boolean result = orderFormValidator.validate(orderForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfSeatIdInvalid() {
        int flightId = 1;
        int wagonId = 1;
        int seatId = 0;
        int userId = 1;
        orderForm = new OrderForm(flightId, wagonId, seatId, userId);
        boolean result = orderFormValidator.validate(orderForm);
        assertFalse(result);
    }

}