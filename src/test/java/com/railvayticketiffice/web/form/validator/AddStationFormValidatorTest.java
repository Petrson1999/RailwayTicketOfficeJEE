package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddStationForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddStationFormValidatorTest {
    private AddStationFormValidator addStationFormValidator;
    private AddStationForm addStationForm;

    @Before
    public void setUp(){
        addStationFormValidator = new AddStationFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        String name = "name";
        addStationForm = new AddStationForm(name);
        boolean result = addStationFormValidator.validate(addStationForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNameInvalid() {
        String name = "";
        addStationForm = new AddStationForm(name);
        boolean result = addStationFormValidator.validate(addStationForm);
        assertFalse(result);
    }
}