package de.falkharnisch.web.jupa.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Util {

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String calculateEan13(String input) {
        if (input.length() < 11 || input.length() > 12) {
            throw new IllegalArgumentException("Input must be a 11 or 12 long digit string.");
        }
        if (input.length() == 11) {
            input = "0" + input;
        }
        int evens = 0;
        int odds = 0;
        int checkSum;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                //check if number is odd or even
                if (i % 2 == 0) {
                    evens += Integer.parseInt(String.valueOf(c));
                } else {
                    odds += Integer.parseInt(String.valueOf(input.charAt(i)));
                }
            } else {
                throw new IllegalArgumentException("Input must be a 11 or 12 long digit string.");
            }
        }
        odds = odds * 3;
        int total = odds + evens;
        if (total % 10 == 0) {
            checkSum = 0;
        } else {
            checkSum = 10 - (total % 10);
        }
        return input + String.valueOf(checkSum);
    }
}
