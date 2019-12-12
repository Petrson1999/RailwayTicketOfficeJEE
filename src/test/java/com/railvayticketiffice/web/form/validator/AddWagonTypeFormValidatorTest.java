package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddWagonTypeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddWagonTypeFormValidatorTest {
    private AddWagonTypeFormValidator wagonTypeFormValidator;
    private AddWagonTypeForm addWagonTypeForm;

    @Before
    public void setUp() {
        wagonTypeFormValidator = new AddWagonTypeFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        String name = "name";
        int seatsCount = 10;
        addWagonTypeForm = new AddWagonTypeForm(name, seatsCount);
        boolean result = wagonTypeFormValidator.validate(addWagonTypeForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNameInvalid() {
        String name = "";
        int seatsCount = 10;
        addWagonTypeForm = new AddWagonTypeForm(name, seatsCount);
        boolean result = wagonTypeFormValidator.validate(addWagonTypeForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfSeatsCountInvalid() {
        String name = "name";
        int seatsCount = 0;
        addWagonTypeForm = new AddWagonTypeForm(name, seatsCount);
        boolean result = wagonTypeFormValidator.validate(addWagonTypeForm);
        assertFalse(result);
    }
}