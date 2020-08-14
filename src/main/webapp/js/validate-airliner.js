/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var email = document.querySelector('input[name="email"]');
var airliner = document.querySelector('input[name="airliner"]');
var isMailValid = true;
var isNameValid = true;

email.addEventListener("change", function () {
    if (email.value) {
        $.ajax({
            url: 'adminaction.htm',
            type: "POST",
            data: {
                action: "validateAirliner",
                email: email.value
            },
            dataType: 'json',
            success: function (data) {
                if (data.exists === true) {
                    isMailValid = false;
                } else {
                    isMailValid = true;
                }
            },
            error: function (error) {
                console.log('Error ${error}');
            }
        });
    }
});

airliner.addEventListener("change", function () {
    if (airliner.value) {
        $.ajax({
            url: 'adminaction.htm',
            type: "POST",
            data: {
                action: "validateAirlinerName",
                airliner: airliner.value
            },
            dataType: 'json',
            success: function (data) {
                if (data.exists === true) {
                    isNameValid = false;
                } else {
                    isNameValid = true;
                }
            },
            error: function (error) {
                console.log('Error ${error}');
            }
        });
    }
});

function validateForm() {
    if (isMailValid && isNameValid) {
        return true;
    }
    if (!isMailValid) {
        alert(email.value + ' already exits!!!');
        return false;
    }
    if (!isNameValid) {
        alert(airliner.value + ' name already exits!!!');
        return false;
    }
}

