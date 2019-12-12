package com.railvayticketiffice.web.form.validator;

import com.railvayticketiffice.enums.Role;
import com.railvayticketiffice.web.form.request.RegistrationForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationFormValidatorTest {

    private RegistrationFormValidator registrationFormValidator;
    private RegistrationForm registrationForm;

    @Before
    public void setUp() {
        registrationFormValidator = new RegistrationFormValidator();
    }

    @Test
    public void shouldReturnTrueFormValid() {
        String firstName = "firstNme";
        String lastName = "lastName";
        String login = "login@gmail.com";
        String password = "123456";
        String passwordConfirm = "123456";
        Role role = Role.USER;
        registrationForm = new RegistrationForm( firstName,  lastName,  login,  password,  passwordConfirm,  role);
        boolean result = registrationFormValidator.validate(registrationForm);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfPasswordsDontEqualsInvalid() {
        String firstName = "firstNme";
        String lastName = "lastName";
        String login = "login@gmail.com";
        String password = "123456";
        String passwordConfirm = "1234123";
        Role role = Role.USER;
        registrationForm = new RegistrationForm( firstName,  lastName,  login,  password,  passwordConfirm,  role);
        boolean result = registrationFormValidator.validate(registrationForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfPasswordsLengthInvalid() {
        String firstName = "firstNme";
        String lastName = "lastName";
        String login = "login@gmail.com";
        String password = "12345";
        String passwordConfirm = "12345";
        Role role = Role.USER;
        registrationForm = new RegistrationForm( firstName,  lastName,  login,  password,  passwordConfirm,  role);
        boolean result = registrationFormValidator.validate(registrationForm);
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfLoginInvalid() {
        String firstName = "firstNme";
        String lastName = "lastName";
        String login = "login";
        String password = "123456";
        String passwordConfirm = "1234123";
        Role role = Role.USER;
        registrationForm = new RegistrationForm( firstName,  lastName,  login,  password,  passwordConfirm,  role);
        boolean result = registrationFormValidator.validate(registrationForm);
        assertFalse(result);
    }

}