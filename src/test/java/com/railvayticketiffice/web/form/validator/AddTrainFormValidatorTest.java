package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddTrainForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTrainFormValidatorTest {

    private AddTrainFormValidator addTrainFormValidator;
    private AddTrainForm addTrainForm;

    @Before
    public void setUp(){
        addTrainFormValidator = new AddTrainFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        String name = "name";
        addTrainForm = new AddTrainForm(name);
        boolean result = addTrainFormValidator.validate(addTrainForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIdFormInvalid() {
        String name = "";
        addTrainForm = new AddTrainForm(name);
        boolean result = addTrainFormValidator.validate(addTrainForm);
        assertFalse(result);
    }

}