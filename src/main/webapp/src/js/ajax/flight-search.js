$(document).ready(function () {
    $('#form-flights').on("submit", function (e) {
        e.preventDefault();
        let departureStationId = $('#departureStationSelector').val();
        let arrivalStationId = $('#arrivalStationSelector').val();
        let date = $('#datetimepicker').val();
        let time = $('#datetimepicker1').val();

        rememberLastSearch();

        if (searchFormValidation(departureStationId, arrivalStationId, date, time) === false) {
            return;
        }

        $(location).attr('href',
            "flights?" +
            "departureStationId=" + departureStationId + "&" +
            "arrivalStationId=" + arrivalStationId + "&" +
            "date=" + getDate(date,time)
        )
    });

    function searchFormValidation(departureStationId, arrivalStationId, date, time) {
        let valid = true;
        if (departureStationId === arrivalStationId) {
            toastMesseg(false, 'выберите разные станции');
            valid = false;
        }
        if (date.length === 0) {
            toastMesseg(false, 'поле ДАТА не может быть пустым');
            valid = false;
        }
        if (time.length === 0) {
            toastMesseg(false, 'поле ВРЕМЯ не может быть пустым');
            valid = false;
        }
        return valid;
    }

    function getDate(date, time) {
        let hours = Number(time.substring(0 , 2));
        if (time.indexOf('PM') !== -1) {
            hours = hours + 12;
        }
        return date + " " + hours + time.substring(2 , 5);
    }

});

