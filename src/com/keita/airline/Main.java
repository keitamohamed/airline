package com.keita.airline;

import com.keita.airline.bookflat.FlightBooking;
import com.keita.airline.seat.AirLineSeat;
import com.keita.airline.bookflat.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        FlightBooking booking = new FlightBooking();


        List<List<String>> smallSeat;
        List<List<String>> mediumSeat;
        List<List<String>> largeSeat;
        List<Flight> flights = new ArrayList<>();

        Flight flight = new Flight();
        AirLineSeat airlineSeat = new AirLineSeat();

        airlineSeat.is64SeatCreated();
        airlineSeat.is80SeatCreated();
        airlineSeat.is120SeatCreated();


        int choice = 0;

        booking.loadFlat();

        while (choice != 99) {
            choice = menu();
            sc.nextLine();

            switch (choice) {
                case 99:
                    choice = 99;
                    break;
                case 1:
                    choice = booking.printFlat();
                    String seat = booking.createSeat(choice);
                    booking.isSeatUpdated(choice, seat);
                    break;
                case 2:
                    System.out.println("What flight are you searching for?: ");
                    String flightName = sc.nextLine();
                    booking.isFlightAvailable(flightName);
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Please enter a search value: (Only by full name or booking number): ");
                    String searchValue = sc.nextLine();
                    booking.isBookCancle(searchValue);
                    break;
                default:
                    System.out.println("Invalid selection. Please select a valid choice from the list");
            }
        }
    }

    private static int menu() {
        System.out.println("Welcome to Airline Booking\n".toUpperCase() +
                "===========================\n" +
                "1. Book a flight\n" +
                "2. Search for available flight\n" +
                "3. Check booking detail\n" +
                "4. Cancel booking\n" +
                "5. Print category\n" +
                "99. End program\n" +
                "Pick a choice: ");
        return (sc.nextInt());
    }
}
