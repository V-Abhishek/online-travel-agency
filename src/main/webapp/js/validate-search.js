/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var arrivalLocation = document.querySelector('input[name="arrivalLocation"]');
var departureLocation = document.querySelector('input[name="departureLocation"]');
var locationsCheck = true;
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

    if (!locationsCheck) {
        alert('Departure and Arrival Locations cannot be same');
        return false;
    }

    if (locationsCheck) {
        return true;
    }
}




