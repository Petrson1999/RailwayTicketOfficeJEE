package com.railvayticketiffice.web.form.validator;


import com.railvayticketiffice.web.form.request.RegistrationForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFormValidator implements FormValidator<RegistrationForm>{

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean validate(RegistrationForm form) {
        return this.validatePassword(form)
                && this.validateLogin(form);
    }

    private boolean validatePassword(RegistrationForm form) {
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();

        return password != null
                && password.equals(passwordConfirm) && (password.length() >= 6);
    }

    private boolean validateLogin(RegistrationForm form) {
        String login = form.getLogin();
        return login != null && (login.length() > 6) && this.validateEmail(login);
    }


    private boolean validateEmail(final String email) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }


}
