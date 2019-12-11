$(document).ready(function () {
    $('#form-registration').on("submit", function (e) {
        e.preventDefault();
        var name = $('#first-name').val();
        var surname = $('#last-name').val();
        var email = $('#inputEmail').val();
        var password = $('#inputPassword').val();
        var password_confirm = $('#inputConfirmPassword').val();

        if (registerFormValidation(name, surname, email, password, password_confirm) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "registration",
            data: {
                "first_name": name,
                "last_name": surname,
                "login": email,
                "password": password,
                "password_confirm": password_confirm
            },
            success: function (data) {
                if (data.success) {
                    $(location).attr('href', data.url)
                } else {
                    toastMesseg(false, data.message);
                }

            }
        })
    });

    function registerFormValidation(name, surname, email, password, password_confirm) {
        var valid = true;
        if (name === null) {
            toastMesseg(false, 'поле имя не может быть пустым');
            valid = false;
        }
        if (surname === null) {
            toastMesseg(false, 'поле фамилия не может быть пустым');
            valid = false;
        }
        if (email.length < 6 || email === null) {
            toastMesseg(false, 'поле email заполнено некоректно');
            valid = false;
        }
        if (password.length < 6) {
            toastMesseg(false, 'пароль имеет меньше 6 символов');
            valid = false;
        }
        if (password !== password_confirm) {
            toastMesseg(false, 'пароли не совпадают');
            valid = false;
        }
        return valid;
    }

});
