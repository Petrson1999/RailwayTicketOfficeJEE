$(document).ready(function () {
    $('#form-sign-in').on("submit", function (e) {
        e.preventDefault();
        let login = $('#login').val();
        let password = $('#password').val();

        $.ajax({
            type: "POST",
            url: "login",
            data: {"login": login, "password": password},
            success: function (data) {
                if (data.success) {
                    $(location).attr('href', data.url)
                } else {
                    toastMesseg(false,data.message);
                }

            }
        })
    })
});