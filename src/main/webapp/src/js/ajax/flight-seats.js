let flightFreeSeats;

function openSeatModal(flightId) {
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
    if (innerStr.length === 0) {
        document.getElementById("seat").innerHTML = "<option selected>No free places!</option>";
    } else {
        document.getElementById("seat").innerHTML = innerStr;
    }
    return false;
}



