package com.keita.airline.seat;

import java.util.ArrayList;
import java.util.List;

public class AirLineSeat {
    private List<List<String>> smallSeats;
    private List<List<String>> mediumSeat;
    private List<List<String>> largeSeat;

    private final String[] seatRow = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private int num = 0016;

    public AirLineSeat() {
        this.smallSeats = new ArrayList<>();
        this.mediumSeat = new ArrayList<>();
        this.largeSeat = new ArrayList<>();
        num++;
    }

    @Override
    public String toString() {
        return "Small seats " + smallSeats + " medium seats " +
                "" + mediumSeat + " large seats " + largeSeat;
    }

    public List<List<String>> is64SeatCreated() {
        if (createSmallSeat().size() == 0){
            return createSmallSeat();
        }
        return createSmallSeat();
    }

    public List<List<String>> is80SeatCreated() {
        if (createMediumSeat().size() == 0){
            return createMediumSeat();
        }
        return createMediumSeat();
    }

    public List<List<String>> is120SeatCreated() {
        if (createLargeSeat().size() == 0){
            return createLargeSeat();
        }
        return createLargeSeat();
    }

    private List<List<String>> createSmallSeat() {

        smallSeats = new ArrayList<>();
        for (int i = 0; i < seatRow.length; i++) {
            smallSeats.add(new ArrayList<>());
            smallSeats.get(i).add(seatRow[i]);
            for (int j = 1; j <= seatRow.length; j++) {
                smallSeats.get(i).add(seatRow[i] + Integer.toString((j)));
            }
        }
        return smallSeats;
    }

    private List<List<String>> createMediumSeat() {

        mediumSeat = new ArrayList<>();
        for (int i = 0; i < seatRow.length; i++) {
            mediumSeat.add(new ArrayList<>());
            mediumSeat.get(i).add(seatRow[i]);
            for (int j = 1; j <= 10; j++) {
                mediumSeat.get(i).add(seatRow[i] + Integer.toString((j)));
            }
        }
        return mediumSeat;
    }

    private List<List<String>> createLargeSeat() {

        largeSeat = new ArrayList<>();
        for (int i = 0; i < seatRow.length; i++) {
            largeSeat.add(new ArrayList<>());
            largeSeat.get(i).add(seatRow[i]);
            for (int j = 1; j <= 15; j++) {
                largeSeat.get(i).add(seatRow[i] + Integer.toString((j)));
            }
        }
        return largeSeat;
    }

    public List<List<String>> getSmallSeats() {
        return smallSeats;
    }

    public List<List<String>> getMediumSeat() {
        return mediumSeat;
    }

    public List<List<String>> getLargeSeat() {
        return largeSeat;
    }
}
