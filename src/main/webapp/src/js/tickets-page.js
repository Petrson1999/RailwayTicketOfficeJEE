function changeStation() {
    let departureSt = document.getElementById("departureStationSelector").selectedIndex;
    $('#departureStationSelector').get(0).selectedIndex = document.getElementById("arrivalStationSelector").selectedIndex;
    $('#arrivalStationSelector').get(0).selectedIndex = departureSt;
}


function rememberLastSearch() {
    localStorage.setItem("departureStationId", $('#departureStationSelector').get(0).selectedIndex);
    localStorage.setItem("arrivalStationId", $('#arrivalStationSelector').get(0).selectedIndex);
    localStorage.setItem("date", $('#datetimepicker').get(0).value);
    localStorage.setItem("time", $('#datetimepicker1').get(0).value);
}

function setLastSearch() {
    $('#departureStationSelector').get(0).selectedIndex = localStorage.getItem("departureStationId");
    $('#arrivalStationSelector').get(0).selectedIndex = localStorage.getItem("arrivalStationId");
    $('#datetimepicker').get(0).value = localStorage.getItem("date");
    $('#datetimepicker1').get(0).value = localStorage.getItem("time");
    localStorage.removeItem("departureStationId");
    localStorage.removeItem("arrivalStationId");
    localStorage.removeItem("date");
    localStorage.removeItem("time");
}