package com.keita.airline.bookflat;

import com.keita.airline.flightpassanger.Passenger;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String flightName;
    private String flightNum;
    private List<List<String>> seats;
    private List<Passenger> passengers;

    private int airLineNum = 5262;

    public Flight() {

    }

    public Flight(String n, List<List<String>> seat) {
        this.flightName = n;
        this.flightNum = "Air" + airLineNum;
        this.seats = seat;
        this.passengers = new ArrayList<>();
        airLineNum++;
    }

    @Override
    public String toString() {
        return "Flight name " + flightName +  ", number " + flightNum;
    }

    public String flightDetail() {
        return "thank for choosing " + flightName + " as your flight service.\nThe " +
                "flight number is " + flightNum + ", your seat number is ";
    }

    public String flightNameAndNum() {
        return flightName + " " + flightNum;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public List<List<String>> getSeats() {
        return seats;
    }


    public int getAirLineNum() {
        return airLineNum;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
