/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('input[id=wifiAvailable]').change(function () {
    if ($(this).is(':checked')) {
        $('.wifiNotPresent').hide();
    } else {
        $('.wifiNotPresent').show();
    }
});
