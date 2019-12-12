$(document).ready(function () {
    $('#add-wagon-type-form').on("submit", function (e) {
        e.preventDefault();
        let name = $('#type-name').val();
        let seatCount = $('#seats-count').val();

        if (registerFormValidation(name) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "wagon_type",
            data: {
                "type-name": name,
                "seat-count": seatCount
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

    function registerFormValidation(name, seatCount) {
        let valid = true;
        if (name === null || name.length == null) {
            toastMesseg(false, 'поле название не может быть пустым');
            valid = false;
        }
        if (seatCount === 0) {
            toastMesseg(false, 'укажите количество мест');
            valid = false;
        }
        return valid;
    }

});
