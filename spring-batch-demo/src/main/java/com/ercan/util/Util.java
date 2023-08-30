package com.ercan.util;

import java.util.Locale;

public class Util {

    public static String getCountryByCode(String code){
        String[] countries = Locale.getISOCountries();
        for (String country:countries){
            Locale locale = new Locale("en",country);
            if (locale.getCountry().equals(code)){
                return locale.getDisplayCountry();
            }
        }
        return "";
    }
}
