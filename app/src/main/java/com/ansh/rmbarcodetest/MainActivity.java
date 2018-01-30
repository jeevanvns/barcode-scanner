package com.ansh.rmbarcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ansh.barcodelibrary.BarcodeCapture;
import com.ansh.rmbarcodetest.parser.model.IataCode;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Calendar;
import java.util.HashMap;

import parser.ParseException;
import parser.Parser;


public class MainActivity extends AppCompatActivity implements BarcodeCapture.BarCodeListener {
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarcodeCapture.setOnBarcodeListener(this);
        findViewById(R.id.read_barcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarcodeCapture.class));
            }
        });
        findViewById(R.id.btn_barcode_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BarcodeList.class));
            }
        });


    }


    @Override
    public void onSuccess(Barcode barcode, String documentType, HashMap<String, String> data) {

        try {

            IataCode parse1 = new Parser().parse(barcode.rawValue);
            Log.e(TAG, "onSuccess: " + "\n" +
                    "First Name : " + parse1.getPassengerFirstName() + "\n" +
                    "Last Name : " + parse1.getPassengerLastName() + "\n" +
                    "Date : " + parse1.getFirstFlightSegment().getDateOfFlight().get(Calendar.YEAR) + "/"
                    + parse1.getFirstFlightSegment().getDateOfFlight().get(Calendar.MONTH) + "/" +
                    parse1.getFirstFlightSegment().getDateOfFlight().get(Calendar.DATE) + "\n" +
                    "Flight No : " + parse1.getFirstFlightSegment().getFlightNumber() + "\n" +
                    "To : " + parse1.getFirstFlightSegment().getToCity() + "\n" +
                    "From : " + parse1.getFirstFlightSegment().getFromCity() + "\n" +
                    "Julian Date : " + parse1.getFirstFlightSegment().getJulianDateOfFlight() + "\n" +
                    "Airline : " + parse1.getFirstFlightSegment().getOperatingCarrierDesignator() + "\n" +
                    "PNR : " + parse1.getFirstFlightSegment().getPNR() + "\n" +
                    "Status : " + parse1.getFirstFlightSegment().getPassengerStatus() + "\n" +
                    "Class : " + parse1.getFirstFlightSegment().getCompartmentCode() + "\n" +
                    "Seat No : " + parse1.getFirstFlightSegment().getSeatNumber() + "\n");
            //   IataCode parse = new Parser().strict().parse(barcode.rawValue);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed: ");
        }
        //   Log.e(TAG, "onSuccess: "+data.values());
        //  Toast.makeText(this, "barcode", Toast.LENGTH_SHORT).show();
        //    Toast.makeText(MainActivity.this, "barcode read", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(String error) {

    }
}
