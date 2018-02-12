package com.ansh.barcode.parser;

import com.ansh.bpbarcodeparser.main.Parser;
import com.ansh.bpbarcodeparser.model.IataCode;

import java.util.Calendar;

/**
 * Created by Jeevan Gupta on 01-11-2017.
 * {@link BoardingPassParser}
 */


public class BoardingPassParser {
    private String rawData;

    public BoardingPassParser(String data) {
        this.rawData = data;
    }

    public BoardingPass parse() {
        try {
            IataCode parse = new Parser().parse(rawData);


            int[] ints = Pdf417DataParser.fromJulian(062);
            BoardingPass barcode = new BoardingPass();
            barcode.setName(parse.getPassengerFirstName() + " " + parse.getPassengerLastName());
            barcode.setBookingReference(parse.getFirstFlightSegment().getPNR());
            barcode.setDepartureAirportIATA(parse.getFirstFlightSegment().getFromCity());
            barcode.setArrivingAirportIATA(parse.getFirstFlightSegment().getToCity());
            barcode.setAirlineIATA(parse.getFirstFlightSegment().getOperatingCarrierDesignator());
            barcode.setFlightNo(parse.getFirstFlightSegment().getFlightNumber());
            //  Calendar dateOfFlight = Utils.parseJulian3digitsDate();
            Calendar cal = parse.getFirstFlightSegment().getDateOfFlight();
            barcode.setDepartureDay(cal.get(Calendar.DAY_OF_MONTH));
            barcode.setDepartuteMonth((cal.get(Calendar.MONTH) + 1));
            barcode.setDepartureYear(cal.get(Calendar.YEAR));
            barcode.setCabin(parse.getFirstFlightSegment().getCompartmentCode().getDescription());
            barcode.setSeatNo(parse.getFirstFlightSegment().getSeatNumber());
            return barcode;
        } catch (com.ansh.bpbarcodeparser.main.ParseException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
