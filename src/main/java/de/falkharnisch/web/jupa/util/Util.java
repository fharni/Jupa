package de.falkharnisch.web.jupa.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Util {

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String calculateEan13(String input) {
        if (input.length() == 11) {
            input = "0" + input;
        }
        int evens = 0; //initialize evens variable
        int odds = 0; //initialize odds variable
        int checkSum; //initialize the checkSum
        for (int i = 0; i < input.length(); i++) {
            //check if number is odd or even
            if (i % 2 == 0) { // check that the character at position "i" is divisible by 2 which means it's even
                evens += Integer.parseInt(String.valueOf(input.charAt(i)));// then add it to the evens
            } else {
                odds += Integer.parseInt(String.valueOf(input.charAt(i))); // else add it to the odds
            }
        }
        odds = odds * 3; //multiply odds by three
        int total = odds + evens; //sum odds and evens
        if (total % 10 == 0) { //if total is divisible by ten, special case
            checkSum = 0;//checksum is zero
        } else { //total is not divisible by ten
            checkSum = 10 - (total % 10); //subtract the ones digit from 10 to find the checksum
        }
        return input + String.valueOf(checkSum);
    }
}
