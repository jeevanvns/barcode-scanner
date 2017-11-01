package com.ansh.rmbarcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ansh.barcodelibrary.BarcodeCapture;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.HashMap;


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

    }


    @Override
    public void onSuccess(Barcode barcode, String documentType, HashMap<String, String> data) {
        Log.e(TAG, "onSuccess: ");

    }

    @Override
    public void onFailure(String error) {

    }
}
