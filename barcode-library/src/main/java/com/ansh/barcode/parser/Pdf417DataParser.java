package com.ansh.barcode.parser;

import android.util.Log;

import com.ansh.barcode.util.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Jeevan Gupta on 01-11-2017.
 * {@link Pdf417DataParser}
 */

public class Pdf417DataParser {
    public static HashMap<String, String> BoardingPass(String rawData) {
        String[] barcodeDataArray = rawData.replaceAll("\\s+", " ").split(" ");
        StringBuilder temp = new StringBuilder();
        for (String s : barcodeDataArray) {
            temp.append(" ").append(s);
        }
        Log.e("Parser", "BoardingPass: PDF417 " + temp);
        Log.e("Parser", "BoardingPass: " + rawData);
        try {
            HashMap<String, String> data = new HashMap<>();
            data.put("name", barcodeDataArray[0]);
            data.put("bookingReference", barcodeDataArray[1]);
            data.put("departureAirportIATA", barcodeDataArray[2].substring(0, 3));
            data.put("setArrivingAirportIATA", barcodeDataArray[2].substring(3, 6));
            data.put("setAirlineIATA", barcodeDataArray[2].substring(6, 8));
            data.put("flightNo", barcodeDataArray[3]);
            Calendar cal = Utils.parseJulian3digitsDate(barcodeDataArray[4].substring(0, 3));
            data.put("day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            data.put("month", String.valueOf((cal.get(Calendar.MONTH) + 1)));
            data.put("year", String.valueOf(cal.get(Calendar.YEAR)));
            data.put("cabin", barcodeDataArray[4].substring(3, 4));
            data.put("seatNo", barcodeDataArray[4].substring(4, barcodeDataArray[4].length()));
            return data;
        } catch (ParseException | IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }
}
