$(document).ready(function () {
    $('#add-train-form').on("submit", function (e) {
        e.preventDefault();
        let name = $('#train').val();

        if (addTrainFormValidation(name) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "trains",
            data: {
                "train_name": name,
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

    function addTrainFormValidation(name) {
        return name.length !== 0;

    }

});
