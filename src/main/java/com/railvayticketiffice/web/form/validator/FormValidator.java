package com.railvayticketiffice.web.form.validator;

public interface FormValidator<T> {
    
     boolean validate(T form);
}
