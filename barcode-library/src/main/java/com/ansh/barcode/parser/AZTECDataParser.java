package com.ansh.barcode.parser;

import com.ansh.barcode.util.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Jeevan Gupta on 01-11-2017.
 * {@link AZTECDataParser}
 */


public class AZTECDataParser {

    /**
     * this method  is static u can use this using (.) with class name, this method parse azteck type of barcode data into boarding pass data
     * exp - name ,booking reference no, dep airport ,arrival airport, flight no etc.
     * @param rawData this is the barcode scan raw data
     * @return Boarding Pass Scan HashMap data , using key value you get the value
     */
    public static HashMap<String, String> BoardingPass(String rawData) {
        String[] barcodeDataArray = rawData.replaceAll("\\s+", " ").split(" ");
        //todo define pattern matcher and  match pattern with raw data,handle exception
        try {
            HashMap<String, String> data = new HashMap<>();
            data.put("name", (barcodeDataArray[0] + "/" + barcodeDataArray[1]));
            data.put("bookingReference", barcodeDataArray[2]);
            data.put("departureAirportIATA", barcodeDataArray[3].substring(0, 3));
            data.put("setArrivingAirportIATA", barcodeDataArray[3].substring(3, 6));
            data.put("setAirlineIATA", barcodeDataArray[3].substring(6, 8));
            data.put("flightNo", barcodeDataArray[4]);
            Calendar cal = Utils.parseJulian3digitsDate(barcodeDataArray[5].substring(0, 3));
            data.put("day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            data.put("month", String.valueOf((cal.get(Calendar.MONTH) + 1)));
            data.put("year", String.valueOf(cal.get(Calendar.YEAR)));
            data.put("cabin", barcodeDataArray[5].substring(3, 4));
            data.put("seatNo", barcodeDataArray[5].substring(4, barcodeDataArray[5].length()));
            return data;
        } catch (ParseException e) {
            return null;
        }
    }
}
