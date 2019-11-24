package com.railvayticketiffice.web.form.validator;


import com.railvayticketiffice.web.form.RegistrationForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFormValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(RegistrationForm form) {
        return validatePassword(form)
                && validateLogin(form);
    }

    private static boolean validatePassword(RegistrationForm form) {
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();

        return password != null
                && password.equals(passwordConfirm) && (password.length() >= 6);
    }

    private static boolean validateLogin(RegistrationForm form) {
        String login = form.getLogin();
        return login != null && (login.length() > 6) && validateEmail(login);
    }


    private static boolean validateEmail(final String email) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }


}
