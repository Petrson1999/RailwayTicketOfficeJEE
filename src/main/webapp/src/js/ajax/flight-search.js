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
            "date=" + getDate(date, time)
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

        let today = new Date();
        let selectedDate = new Date();
        selectedDate.setFullYear(Number(date.substring(6, 10)));
        selectedDate.setMonth(Number(date.substring(3, 5)) - 1);
        selectedDate.setDate(Number(date.substring(0, 2)));
        selectedDate.setHours(Number(getTime(time).substring(0, 2)));
        selectedDate.setMinutes(Number(getTime(time).substring(3, 5)));

        if (
            selectedDate.valueOf() < today.valueOf()
        ) {
            toastMesseg(false, 'выбранная дата уже прошла');
            valid = false;
        }
        return valid;
    }

    function getDate(date, time) {
        let hours = Number(time.substring(0, time.length - 6));
        if (time.indexOf('PM') !== -1) {
            hours = hours + 12;
        }
        return date + "T" + hours + time.substring(time.length - 6, 5);
    }

    function getTime(time) {
        let hours = Number(time.substring(0, time.length - 6));
        if (time.indexOf('PM') !== -1) {
            hours = hours + 12;
        }
        if(hours < 10){
            hours = "0" + hours;
        }
        return hours + time.substring(time.length - 6, 5);
    }

});

