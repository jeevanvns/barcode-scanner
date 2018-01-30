package com.ansh.rmbarcodetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.barcode.parser.Pdf417DataParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jeevan Gupta on 29-01-2018.
 * {@link BarcodeList}
 */

public class BarcodeListAdapter extends RecyclerView.Adapter<BarcodeListAdapter.BarcodeViewHolder> {
    private Context mContext;
    private ArrayList<String> mList;

    public BarcodeListAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public BarcodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BarcodeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_barcode, parent, false));
    }

    @Override
    public void onBindViewHolder(BarcodeViewHolder holder, int position) {
        HashMap<String, String> boardingPassScan = Pdf417DataParser.BoardingPass(mList.get(position));
        Log.e("", "onBindViewHolder: "+position + " data: "+mList.get(position) );
        holder.tvName.setText((boardingPassScan.get("name") != null) ? boardingPassScan.get("name") : "--");
        holder.tvBookingRef.setText((boardingPassScan.get("bookingReference") != null) ? boardingPassScan.get("bookingReference") : "--");
        holder.tvDepAir.setText((boardingPassScan.get("departureAirportIATA") != null) ? boardingPassScan.get("departureAirportIATA") : "--");
        holder.tvAirAir.setText((boardingPassScan.get("setArrivingAirportIATA") != null) ? boardingPassScan.get("setArrivingAirportIATA") : "--");
        holder.tvAirName.setText((boardingPassScan.get("setAirlineIATA") != null) ? boardingPassScan.get("setAirlineIATA") : "--");
        holder.tvFlightNo.setText((boardingPassScan.get("flightNo") != null) ? boardingPassScan.get("flightNo") : "--");
        holder.tvDepDate.setText((boardingPassScan.get("day") != null) ? (boardingPassScan.get("day") + "-" +
                boardingPassScan.get("month") + "-" + boardingPassScan.get("year")) : "--");
        holder.tvCabin.setText((boardingPassScan.get("cabin") != null) ? boardingPassScan.get("cabin") : "--");
        holder.tvSeatNo.setText((boardingPassScan.get("seatNo") != null) ? boardingPassScan.get("seatNo") : "--");

    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    public class BarcodeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvBookingRef;
        private TextView tvDepAir;
        private TextView tvAirAir;
        private TextView tvAirName;
        private TextView tvFlightNo;
        private TextView tvDepDate;
        private TextView tvCabin;
        private TextView tvSeatNo;
        public BarcodeViewHolder(View itemView) {
            super(itemView);
            tvName = ((TextView) itemView.findViewById(R.id.tv_name));
            tvBookingRef = ((TextView) itemView.findViewById(R.id.tv_booking_ref));
            tvDepAir = ((TextView) itemView.findViewById(R.id.tv_dep_air));
            tvAirAir = ((TextView) itemView.findViewById(R.id.tv_air_air));
            tvAirName = ((TextView) itemView.findViewById(R.id.tv_air_name));
            tvFlightNo = ((TextView) itemView.findViewById(R.id.tv_flight_no));
            tvDepDate = ((TextView) itemView.findViewById(R.id.tv_departure_date));
            tvCabin = ((TextView) itemView.findViewById(R.id.tv_cabin));
            tvSeatNo = ((TextView) itemView.findViewById(R.id.tv_seat_no));
        }
    }
}
