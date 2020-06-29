package com.mars.robotproject.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CihazUtil {

    public static int convertHexToInt(String hexValue) {
        if (hexValue.indexOf("#") != -1) {
            hexValue = hexValue.replace("#", "");
        }
        Long d = Long.parseLong(hexValue, 16);
        return d.intValue();
    }

    public static int convertHexToInt32(String hexValue) {
        if (hexValue.indexOf("#") != -1) {
            hexValue = hexValue.replace("#", "");
        }
        Long d = Long.parseLong(hexValue, 16);
        return d.intValue();
    }

    public static String convertIntToHexString(int sayi, int digit) {
        String deger = Integer.toHexString(sayi);
        while (deger.length() < digit) {
            deger = "0" + deger;
        }
        return deger.toUpperCase();//autoPadOrShrink(Integer.toHexString(sayi), 4);
    }

    public static double GetLatitudeFromEventString(String Latitude) {//enlem
        double la = Double.parseDouble(Latitude.substring(1, 3))
                + Double.parseDouble(Latitude.substring(3, 5)) / 60
                + Double.parseDouble(Latitude.substring(6, 10)) / 600000;
        if (Latitude.indexOf("-") >= 0)
            la = -1 * la;
        return la;
    }


    public static double GetLongitudeFromEventString(String Longitude) {//boylam
        double lo = Double.parseDouble(Longitude.substring(1, 4))
                + Double.parseDouble(Longitude.substring(4, 6)) / 60
                + Double.parseDouble(Longitude.substring(7, 11)) / 600000;
        if (Longitude.indexOf("-") >= 0)
            lo = -1 * lo;
        return lo;
    }

    public static List<Integer> convertDecimalToBit(String dsrc) {
        String decimalVal = "";
        List<Integer> bits = new ArrayList<>();
        for (int i = 0; i < dsrc.length(); i++) {
            decimalVal += convertIntToHexString(String.valueOf(dsrc.charAt(i)), 4);
        }
        char[] chars = decimalVal.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int c = Integer.parseInt(String.valueOf(chars[i]));
            if (c == 1) bits.add(Math.abs(i - 31));
        }
        return bits;
    }
    public static String convertIntToHexString(String chars, int digit) {
        int sayi = Integer.parseInt(chars);
        String deger = StringFormatLen(Integer.toBinaryString(sayi), 4);
        while (deger.length() < digit) {
            deger = "0" + deger;
        }
        return deger.toUpperCase();//autoPadOrShrink(Integer.toHexString(sayi), 4);
    }
    public static String StringFormatLen(String value, int len1) {
        while (value.length() < len1) {
            value = "0" + value;
        }
        return value;
    }
    public static String StringFormatDate(String tarih, String saat) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmmss");//yyyy-MM-dd HH:mm
        LocalDateTime localDateTime = LocalDateTime.parse(tarih + " " + saat, formatter);
        localDateTime = localDateTime.plusHours(2);

        return localDateTime.format(formatter);
    }
    public static String getLatitude(String enlem) {
        Double enlemSonuc = Double.parseDouble(enlem.substring(0, 2)) + Double.parseDouble(enlem.substring(2, 4) + "." + enlem.substring(4, 8)) / 60;
        String enlemResult = String.valueOf(enlemSonuc);
        enlemResult = enlemResult.length() > 9 ? enlemResult.substring(0, 9) : enlemResult;
        return enlemResult;
    }

    public static String getLongitude(String boylam) {
        Double boylamSonuc = Double.parseDouble(boylam.substring(0, 2)) + Double.parseDouble(boylam.substring(2, 4) + "." + boylam.substring(4, 8)) / 60;
        String boylamResult = String.valueOf(boylamSonuc);
        boylamResult = boylamResult.length() > 9 ? boylamResult.substring(0, 9) : boylamResult;
        return boylamResult;
    }

    public static String getHiz(String hiz) {
        String resultHiz = String.valueOf(convertHexToInt(hiz));
        resultHiz = resultHiz.length() == 3 ? resultHiz.substring(0, 1) + "." + resultHiz.substring(1, 3) :
                resultHiz.length() == 4 ? resultHiz.substring(0, 2) + "." + resultHiz.substring(2, 4) : resultHiz;
        return String.valueOf(Double.parseDouble(resultHiz) * (1.852));
    }

    public static String getMesafe(String mesafe) {
        String resultMesafe = String.valueOf(convertHexToInt(mesafe));
        return String.valueOf(((Double.parseDouble(resultMesafe) / 16) * (1.852)));
    }
}
