/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var serialNumber = document.querySelector('input[name="serialNumber"]');
var conditionChecked = true;

serialNumber.addEventListener("change", function () {
    if (serialNumber.value) {
        $.ajax({
            url: 'airliner.htm',
            type: "POST",
            data: {
                action: "validateSerialNumber",
                serialNumber: serialNumber.value
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

function validateForm() {
    if (conditionChecked) {
        return true;
    } else {
        alert(serialNumber.value + ' serial number already exits!!!');
        return false;
    }
}
