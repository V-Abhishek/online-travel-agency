/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var flightNumber = document.querySelector('input[name="flightNumber"]');
var arrivalLocation = document.querySelector('input[name="arrivalLocation"]');
var departureLocation = document.querySelector('input[name="departureLocation"]');
var start = document.querySelector('input[name="start"]');
var date = document.querySelector('input[name="date"]');
var locationsCheck = true;
var departureTimeCheck = true;
var conditionChecked = true;

flightNumber.addEventListener("change", function () {
    if (flightNumber.value) {
        $.ajax({
            url: 'flight.htm',
            type: "POST",
            data: {
                action: "validateFlightNumber",
                flightNumber: flightNumber.value
            },
            dataType: 'json',
            success: function (data) {
                if (data.exists === true) {
                    conditionChecked = false;
                } else {
                    conditionChecked = true;
                }
            },
            error: function (error) {
                console.log('Error ${error}');
            }
        });
    }
});

start.addEventListener("change", function () {
    if (start.value) {
        $.ajax({
            url: 'flight.htm',
            type: "POST",
            data: {
                action: "validateDeparture",
                start: start.value,
                date: date.value
            },
            dataType: 'json',
            success: function (data) {
                if (data.exists === true) {
                    departureTimeCheck = false;
                } else {
                    departureTimeCheck = true;
                }
            },
            error: function (error) {
                console.log('Error ${error}');
            }
        });
    }
});

departureLocation.addEventListener("change", function () {
    if (departureLocation.value === arrivalLocation.value) {
        locationsCheck = false;
    } else {
        locationsCheck = true;
    }
});

arrivalLocation.addEventListener("change", function () {
    if (departureLocation.value === arrivalLocation.value) {
        locationsCheck = false;
    } else {
        locationsCheck = true;
    }
});

function validateForm() {
    if (!conditionChecked) {
        alert(flightNumber.value + ' flight number already exits!!!');
        return false;
    }
    if (!locationsCheck) {
        alert('Departure and Arrival Locations cannot be same');
        return false;
    }
    if (!departureTimeCheck) {
        alert('There is a time Conflict with the departure time');
        return false;
    }
    if (conditionChecked && locationsCheck && departureTimeCheck) {
        return true;
    }
}


