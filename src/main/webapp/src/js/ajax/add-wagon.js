$(document).ready(function () {
    $('#add-wagon-form').on("submit", function (e) {
        e.preventDefault();
        let name = $('#wagon-name').val();
        let typeId = $('#wagon-types').val();

        if (addWagonValidation(name, typeId) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "wagons",
            data: {
                "wagon_name": name,
                "type-id" : typeId
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

    function addWagonValidation(name, typeId) {
        let valid = true;
        if (name === null || name.length == null) {
            toastMesseg(false, 'поле название не может быть пустым');
            valid = false;
        }
        if (typeId === 0) {
            toastMesseg(false, 'выберите тип вагона');
            valid = false;
        }
        return valid;
    }

});
