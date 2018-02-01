package com.ansh.rmbarcodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ansh.barcode.parser.BoardingPass;
import com.ansh.barcodelibrary.BarcodeCapture;


public class MainActivity extends AppCompatActivity /*implements BarcodeCapture.BarCodeListener*/ {
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*BarcodeCapture.setOnBarcodeListener(this);*/
        findViewById(R.id.read_barcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, BarcodeCapture.class), BarcodeCapture.SCAN);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BarcodeCapture.SCAN && resultCode == Activity.RESULT_OK) {
            Log.e(TAG, "onActivityResult: ");
            BoardingPass boardingPass = data.getParcelableExtra(BarcodeCapture.BarcodeObject);
            Toast.makeText(this, boardingPass.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "This was a problem with barcode.please try again", Toast.LENGTH_SHORT).show();
        }
    }

 /*   @Override
    public void onSuccess(Barcode barcode, String documentType, BoardingPass data) {
        Log.e(TAG, "onSuccess: " + data.toString());
    }

    @Override
    public void onFailure(final Barcode barcode, final String error) {
        Log.e(TAG, "onFailure: " + error);


    }*/
}
