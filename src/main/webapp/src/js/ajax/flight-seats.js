let flightFreeSeats;
let flight;
let dateFlight;

function openSeatModal(flightId, whereToWhere, date) {
    flight = whereToWhere;
    dateFlight = date;
    $.ajax({
        type: "GET",
        url: "seats",
        data: {
            "flightsId": flightId
        },
        success: function (data) {
            if (data.success) {
                flightFreeSeats = data.wagonDtos;
                let innerStr = "";
                for (let wagon of flightFreeSeats) {
                    innerStr = innerStr + "<option value=" + wagon.id + ">" + wagon.name + " " + wagon.wagonType;
                    if (wagon.seats.length === 0) {
                        innerStr = innerStr + " No free places!"
                    }
                    innerStr = innerStr + "</option>";
                }
                document.getElementById("wagon").innerHTML = innerStr;
                document.getElementById("flightInfo").innerHTML =
                    "<li class=\"list-group-item\" value=" + "\"" + flightId + "\"" + " id='flightId'" + ">" + whereToWhere + "</li>" +
                    "<li class=\"list-group-item\" id='date'" + ">" + date + "</li>";
                selectWagon(flightFreeSeats[0].id);
                $('#ModalSeat').modal('show');
            } else {
                toastMesseg(false, data.message);
            }
        }
    });
    return false;
}

function selectWagon(wagonId) {
    let innerStr = "";
    for (let wagon of flightFreeSeats) {
        if (wagon.id === Number(wagonId)) {
            for (let seat of wagon.seats) {
                innerStr = innerStr + "<option value=" + seat.id + ">" + seat.placeNumber + "</option>";
            }
        }
    }
    document.getElementById("seat").innerHTML = innerStr.length === 0 ? `<option selected>No free places!</option>` : innerStr;
    return false;
}

function order() {
    let whereToWhere = flight;
    let date = dateFlight;
    let wagon = $('#wagon').val();
    let seat = $('#seat').val();
    if (validateOrder(wagon , seat) === false) {
        return;
    }
    document.getElementById("orderInfo").innerHTML =
        `<li class="list-group-item">flight : ${whereToWhere}</li><li class="list-group-item">date : ${date}</li><li class="list-group-item">wagon : ${wagon}</li><li class="list-group-item">seat : ${seat}</li>`
    ;
    $('#ModalOrder').modal('show');

}

function validateOrder( wagonId, seatId) {
    let valid = true;
    if (wagonId === 0) {
        toastMesseg(false, 'выберите вагон');
        valid = false;
    }
    if (seatId.length > 2) {
        toastMesseg(false, 'выберите место');
        valid = false;
    }
    return valid;
}



