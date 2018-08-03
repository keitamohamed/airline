package com.keita.airline;

import com.keita.airline.bookflat.Booking;
import com.keita.airline.seat.AirLineSeat;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Booking booking = new Booking();

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
                    booking.addNewFlight();
                    break;
                case 2:
                    choice = booking.printFlat();
                    String seat = booking.getFlightAndPrint(choice);
                    booking.isSeatUpdated(choice, seat);
                    break;
                case 3:
                    System.out.println("What flight are you searching for?: ");
                    String flightName = sc.nextLine();
                    booking.isFlightAvailable(flightName);
                    break;
                case 4:
                    System.out.println(("All information might not be available base\non search key or value\n" +
                            "===========================================").toUpperCase());
                    booking.checkBookingDetail();
                    break;
                case 5:
                    System.out.println("Enter search value: (By: name or booking number): ");
                    String searchValue = sc.nextLine();
                    booking.isBookCancel(searchValue);
                    break;
                case 6:
                    while (choice != 4) {
                        choice = printCategory();
                        sc.nextLine();
                        switchPrintOption(booking, choice);
                    }
                    break;
                default:
                    System.out.println("Invalid selection. Please select a valid choice from the list");
            }
        }
    }

    private static int menu() {
        System.out.println("\nMK: Welcome to Airline Booking\n".toUpperCase() +
                "==============================\n" +
                "1. Add new flight\n" +
                "2. Book a flight\n" +
                "3. Search for available flight\n" +
                "4. Check booking detail\n" +
                "5. Cancel booking\n" +
                "6. Print category\n" +
                "99. End program\n" +
                "Pick a choice: ");
        return (sc.nextInt());
    }

    private static void switchPrintOption(Booking booking, int choice) {
        switch (choice) {
            case 1:
                booking.printPassengerByFlight();
                break;
            case 2:
                choice = booking.printFlat();
                booking.printFlightSeat(choice);
                break;
            case 3:
                booking.printFlightSeat();
                break;
            default:
        }
    }

    private static int printCategory() {
        System.out.println("List of print option\n".toUpperCase() +
                "===========================\n" +
                "1. Print Passenger by flight\n" +
                "2. Print flight seats\n" +
                "3. Print all flight seats\n" +
                "4. Return to main menu\n" +
                "Chose from the list: ");
        return (sc.nextInt());
    }
}
