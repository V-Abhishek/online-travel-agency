/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Abhishek
 */
public class FieldValidations {

    public boolean validateNames(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+([\\s][a-zA-Z]+)*");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validatePhoneNumber(String s) {
        Pattern p = Pattern.compile("\\d{10}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validateEmailAddress(String s) {
        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validatePassword(String s) {
        Pattern p = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validateAplhaNumeric(String s) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+([\\s][a-zA-Z0-9]+)*");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validateZipCode(String s) {
        Pattern p = Pattern.compile("(^(?!0{5})(\\d{5})(?!-?0{4})(|-\\d{4})?$)");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validatePrice(String s) {
        Pattern p = Pattern.compile("[1-9]\\d*(\\.\\d+)?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validateDuration(String s) {
        Pattern p = Pattern.compile("[1-9]\\d*(\\.\\d+)?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public boolean validateDate(String s) {
        Pattern p = Pattern.compile("^(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
