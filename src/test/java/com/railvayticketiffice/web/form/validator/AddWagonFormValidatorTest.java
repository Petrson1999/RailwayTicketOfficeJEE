package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.web.form.request.AddWagonForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddWagonFormValidatorTest {
    private AddWagonFormValidator addWagonFormValidator;
    private AddWagonForm addWagonForm;

    @Before
    public void setUp() {
        addWagonFormValidator = new AddWagonFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        String name = "name";
        int typeId = 1;
        addWagonForm = new AddWagonForm(name, typeId);
        boolean result = addWagonFormValidator.validate(addWagonForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfNameInvalid() {
        String name = "";
        int typeId = 1;
        addWagonForm = new AddWagonForm(name, typeId);
        boolean result = addWagonFormValidator.validate(addWagonForm);
        assertTrue(result);
    }


}