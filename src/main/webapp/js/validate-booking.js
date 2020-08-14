/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var validType = document.getElementById('seattype');
$(document).ready(function () {
    $('#seattype').change(function () {
        $(this).find("option:selected").each(function () {
            var optionValue = $(this).attr("value");
            if (optionValue === 'w') {
                $('.windowseat').show();
                $('.aisleseat').hide();
                $('.middleseat').hide();
            } else if (optionValue === 'a') {
                $('.windowseat').hide();
                $('.aisleseat').show();
                $('.middleseat').hide();
            } else if (optionValue === 'm') {
                $('.windowseat').hide();
                $('.aisleseat').hide();
                $('.middleseat').show();
            }
        });
    }).change();
});

function validateForm() {
    if (validType.value === 'Please select a seat type') {
        alert('Select a valid seat type');
        return false;
    } else {
        return true;
    }
}