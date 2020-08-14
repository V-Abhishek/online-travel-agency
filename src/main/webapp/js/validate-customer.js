/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var email = document.querySelector('input[name="email"]');
var conditionChecked = true;

email.addEventListener("change", function () {
    if (email.value) {
        $.ajax({
            url: 'adminaction.htm',
            type: "POST",
            data: {
                action: "validateCustomer",
                email: email.value
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
        alert(email.value + ' already exits!!!');
        return false;
    }
}

