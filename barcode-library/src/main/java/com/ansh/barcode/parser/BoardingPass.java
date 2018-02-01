package com.ansh.barcode.parser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeevan Gupta on 01-02-2018.
 * {@link BoardingPass}
 */

public class BoardingPass implements Parcelable {
    public static final String TAG = BoardingPass.class.getSimpleName();

    private String name;
    private String bookingReference;
    private String departureAirportIATA;
    private String arrivingAirportIATA;
    private String airlineIATA;
    private String flightNo;
    private Integer departureDay;
    private Integer departuteMonth;
    private Integer departureYear;
    private String cabin;
    private String seatNo;
    private String status;
    private String isDeniedBoarding;

    public String isDeniedBoarding() {
        return isDeniedBoarding;
    }

    public void setDeniedBoarding(String deniedBoarding) {
        isDeniedBoarding = deniedBoarding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BoardingPass() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setDepartureAirportIATA(String departureAirportIATA) {
        this.departureAirportIATA = departureAirportIATA;
    }

    public void setArrivingAirportIATA(String arrivingAirportIATA) {
        this.arrivingAirportIATA = arrivingAirportIATA;
    }

    public void setAirlineIATA(String airlineIATA) {
        this.airlineIATA = airlineIATA;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setDepartureDay(Integer departureDay) {
        this.departureDay = departureDay;
    }

    public void setDepartuteMonth(Integer departuteMonth) {
        this.departuteMonth = departuteMonth;
    }

    public void setDepartureYear(Integer departureYear) {
        this.departureYear = departureYear;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getName() {
        return name;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getDepartureAirportIATA() {
        return departureAirportIATA;
    }

    public String getArrivingAirportIATA() {
        return arrivingAirportIATA;
    }

    public String getAirlineIATA() {
        return airlineIATA;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public Integer getDepartureDay() {
        return departureDay;
    }

    public Integer getDepartuteMonth() {
        return departuteMonth;
    }

    public Integer getDepartureYear() {
        return departureYear;
    }

    public String getCabin() {
        return cabin;
    }

    public String getSeatNo() {
        return seatNo;
    }


    @Override
    public String toString() {
        return "BoardingPass{" +
                "name='" + name + '\'' +
                ", bookingReference='" + bookingReference + '\'' +
                ", departureAirportIATA='" + departureAirportIATA + '\'' +
                ", arrivingAirportIATA='" + arrivingAirportIATA + '\'' +
                ", airlineIATA='" + airlineIATA + '\'' +
                ", flightNo='" + flightNo + '\'' +
                ", departureDay=" + departureDay +
                ", departuteMonth=" + departuteMonth +
                ", departureYear=" + departureYear +
                ", cabin='" + cabin + '\'' +
                ", seatNo='" + seatNo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.bookingReference);
        dest.writeString(this.departureAirportIATA);
        dest.writeString(this.arrivingAirportIATA);
        dest.writeString(this.airlineIATA);
        dest.writeString(this.flightNo);
        dest.writeValue(this.departureDay);
        dest.writeValue(this.departuteMonth);
        dest.writeValue(this.departureYear);
        dest.writeString(this.cabin);
        dest.writeString(this.seatNo);
        dest.writeString(this.status);
        dest.writeString(this.isDeniedBoarding);
    }

    protected BoardingPass(Parcel in) {
        this.name = in.readString();
        this.bookingReference = in.readString();
        this.departureAirportIATA = in.readString();
        this.arrivingAirportIATA = in.readString();
        this.airlineIATA = in.readString();
        this.flightNo = in.readString();
        this.departureDay = (Integer) in.readValue(Integer.class.getClassLoader());
        this.departuteMonth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.departureYear = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cabin = in.readString();
        this.seatNo = in.readString();
        this.status = in.readString();
        this.isDeniedBoarding = in.readString();


    }

    public static final Parcelable.Creator<BoardingPass> CREATOR = new Parcelable.Creator<BoardingPass>() {
        @Override
        public BoardingPass createFromParcel(Parcel source) {
            return new BoardingPass(source);
        }

        @Override
        public BoardingPass[] newArray(int size) {
            return new BoardingPass[size];
        }
    };
}
