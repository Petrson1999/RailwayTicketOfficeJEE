$(document).ready(function () {
    $('#add-station-form').on("submit", function (e) {
        e.preventDefault();
        let name = $('#station').val();

        if (registerFormValidation(name) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "stations",
            data: {
                "station_name": name,
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

    function registerFormValidation(name) {
        return name.length !== 0;

    }

});
