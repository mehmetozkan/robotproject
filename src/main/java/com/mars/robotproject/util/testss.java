package com.mars.robotproject.util;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import okio.ByteString;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class testss {

    public static void main(String[] args) throws Exception {

        String tarih = "260620";
        String saat = "183349";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy HHmmss");//yyyy-MM-dd HH:mm
        LocalDateTime localDateTime = LocalDateTime.parse(tarih + " " + saat, formatter);
        localDateTime = localDateTime.plusHours(2);// plus(2, ChronoUnit.HOURS);

        tarih = localDateTime.format(formatter).split(" ")[0];
        saat = localDateTime.format(formatter).split(" ")[1];


        //System.out.println(localDateTime);
        //System.out.println(localDateTime.format(formatter));


      /*  40542031
        Enlem : 41.003928 ise 41+54,2031/60 şeklinde hesaplanır.

        Sonuç : 40+0,903385 = 40.903385 şeklinde yazılır.

        Boylam: 28.832238 ise 28+83,2238/60
       */

        String enlem = "029175689".substring(1);
        System.out.println(enlem);
        String sonuc = enlem.substring(0, 2) + " " + Integer.parseInt(enlem.substring(2, 8));
        String sonuc2 = enlem.substring(2, 4) + "." + enlem.substring(4, 8);

        Double sonuc4 = Double.parseDouble(enlem.substring(0, 2)) + Double.parseDouble(sonuc2) / 60;

        System.out.println(sonuc4);

        String hiz = CihazUtil.convertIntToHexString(12, 4) + "";
        System.out.println(Integer.toBinaryString(9));
        System.out.println(Integer.toString(9, 2));
       /* hiz = hiz.substring(0, 2) + "." + hiz.substring(2, 3);
        System.out.println(Double.parseDouble(hiz) * (1.852));
*/
        String ss = "00009009";
        System.out.println(convertDecimalToBit(ss));
    }
    public static List<Integer> convertDecimalToBit(String dsrc){
        String decimalVal = "";
        List<Integer> bits=new ArrayList<>();
        for (int i = 0; i < dsrc.length(); i++) {
            decimalVal += convertIntToHexString(String.valueOf(dsrc.charAt(i)), 4);
        }
        char[] chars = decimalVal.toCharArray();
        for (int i = chars.length-1; i > 0 ; i--) {
            int c = Integer.parseInt(String.valueOf(chars[i]));
            if(c == 1) bits.add(Math.abs(i-31));
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

    //String'in başına 0 ekliyor ( length (int len1) kadar )
    public static String StringFormatLen(String value,int len1){
        while (value.length()<len1){
            value = "0" + value;
        }
        return value;
    }


}
