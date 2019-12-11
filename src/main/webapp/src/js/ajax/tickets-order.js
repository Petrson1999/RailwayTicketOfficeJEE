    function submitOrder(){
        let flightId =$('#flightId').val();
        let wagonId = $('#wagon').val();
        let seatId = $('#seat').val();

        $.ajax({
            type: "POST",
            url: "order",
            data: {"flightId": flightId, "wagonId": wagonId , seatId: seatId},
            success: function (data) {
                if (data.success) {
                    toastMesseg(true, data.message);
                    $('#ModalOrder').modal('hide');
                } else {
                    toastMesseg(false,data.message);
                }
            }
        })
    }

