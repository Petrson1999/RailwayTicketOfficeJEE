$(document).ready(function () {
    $('#add-flights').on("submit", function (e) {
        e.preventDefault();
        let departureStation = $('#departureStationSelector').val();
        let arrivalStation = $('#arrivalStationSelector').val();
        let departureDate = $('#date-departure').val();
        let arrivalDate = $('#date-arrival').val();
        let departureTime = $('#departure-time').val();
        let arrivalTime = $('#arrival-time').val();
        let trainId = $('#trainSelector').val();
        let cost = $('#cost').val();

        if (addFlightsFormValidation(departureStation, arrivalStation, departureDate, arrivalDate, departureTime , arrivalTime , trainId, cost) === false) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "flights",
            data: {
                "departure_station_id": departureStation,
                "arrival_station_id": arrivalStation,
                "departure_date": getDate(departureDate, departureTime),
                "arrival_date": getDate(arrivalDate, arrivalTime),
                "train_id": trainId,
                "cost": cost
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

    function addFlightsFormValidation(departureStation, arrivalStation, departureDate, arrivalDate, departureTime , arrivalTime , trainId , cost) {
        let today = new Date();
        let selectedDepartureDate = new Date();
        selectedDepartureDate.setFullYear(Number(departureDate.substring(6, 10)));
        selectedDepartureDate.setMonth(Number(departureDate.substring(3, 5)) - 1);
        selectedDepartureDate.setDate(Number(departureDate.substring(0, 2)));
        selectedDepartureDate.setHours(Number(getTime(departureTime).substring(0, 2)));
        selectedDepartureDate.setMinutes(Number(getTime(departureTime).substring(3, 5)));

        let selectedArrivalDate = new Date();
        selectedArrivalDate.setFullYear(Number(arrivalDate.substring(6, 10)));
        selectedArrivalDate.setMonth(Number(arrivalDate.substring(3, 5)) - 1);
        selectedArrivalDate.setDate(Number(arrivalDate.substring(0, 2)));
        selectedArrivalDate.setHours(Number(getTime(arrivalTime).substring(0, 2)));
        selectedArrivalDate.setMinutes(Number(getTime(arrivalTime).substring(3, 5)));


        let valid = true;
        if (departureStation === arrivalStation) {
            toastMesseg(false, 'станции не могут быть одинаковыми');
            valid = false;
        }
        if ( selectedDepartureDate.valueOf() > selectedArrivalDate.valueOf()) {
            toastMesseg(false, 'дата отправления раньше даты прибытия');
            valid = false;
        }
        if (selectedArrivalDate.length === 0) {
            toastMesseg(false, 'дата отправления пустая!');
            valid = false;
        }
        if (selectedDepartureDate.length === 0) {
            toastMesseg(false, 'дата прибытия пустая!');
            valid = false;
        }
        if ( selectedArrivalDate.valueOf() < today.valueOf()) {
            toastMesseg(false, 'дата прибытия уже прошла');
            valid = false;
        }
        if ( selectedDepartureDate.valueOf() < today.valueOf()) {
            toastMesseg(false, 'дата отправления уже прошла');
            valid = false;
        }
        if (trainId === null) {
            toastMesseg(false, 'выберите поезд');
            valid = false;
        }
        if(cost.length === 0){
            toastMesseg(false, 'укажите стоимость');
            valid = false;
        }
        return valid;
    }

    function getDate(date, time) {
        let hours = Number(time.substring(0, time.length - 6));
        if (time.indexOf('PM') !== -1) {
            hours = hours + 12;
        }
        if(hours < 10){
            hours = "0" + hours;
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
        return hours + time.substring(time.length - 6, time.length);
    }
});
