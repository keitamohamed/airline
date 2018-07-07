package com.keita.airline.airline;

import com.keita.airline.flightpassanger.Passenger;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String flightName;
    private String flightNum;
    private List<List<String>> seats;
    private List<Passenger> passengers;

    public Flight() {
    }

    public Flight(String n, int airNum, List<List<String>> seat) {
        this.flightName = n;
        this.flightNum = "Air" + airNum;
        this.seats = seat;
        this.passengers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Flight name " + flightName +  ", number " + flightNum;
    }

    public String flightDetail() {
        return "thank for choosing " + flightName + " as your flight service.\n" +
                "Flight number is " + flightNum + ", your seat number is ";
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

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
