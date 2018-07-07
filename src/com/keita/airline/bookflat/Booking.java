package com.keita.airline.bookflat;

import com.keita.airline.flightpassanger.Passenger;
import com.keita.airline.seat.AirLineSeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Booking {

    private List<Flight> flights;
    private Scanner sc = new Scanner(System.in);

    private AirLineSeat airlineSeat;

    public Booking() {
        this.flights = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.airlineSeat = new AirLineSeat();
    }


    public int printFlat() {
        System.out.println("CHOSE A FLIGHT FROM THE LIST\n" +
                "==========================");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightName() + ": " + flights.get(i).getFlightNum());
        }
        System.out.println("Chose a flight: ");
        return (sc.nextInt());
    }

    public String createSeat(int flat) {

        List<List<String>> seats = flights.get(flat - 1).getSeats();
        sc.nextLine();

        System.out.println("PICK AN AVAILABLE SEAT FROM THE LIST\n" +
                "===================================");
        for (List<String> seat : seats) {
            for (int j = 0; j < seat.size(); j++) {
                if (j != 0) {
                    System.out.print(seat.get(j) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Enter a seat number (X mean the seat is taken): ");
        String seat = sc.nextLine();
        while (seat.equalsIgnoreCase("X") || !isSeatAvailable(flights, flat, seat)) {
            System.out.println("Invalid seat. Enter a seat number (Ex: K3): ");
            seat = sc.nextLine();
        }
        return seat;
    }

    public void isFlightAvailable(String flightName) {
        if (availableFlight(flights, flightName) != null) {
            System.out.println("Book a seat on this flight (Yes/No): ");
            String book = sc.nextLine();
            if (book.equalsIgnoreCase("Yes")) {
                String seat = createSeat(flightName);
                isSeatUpdated(flightName, seat);
            }
        }
    }

    public void isSeatUpdated(int flat, String seat) {
        System.out.println("Enter customer name: ");
        String name = sc.nextLine();
        System.out.println("Enter customer email: ");
        String email = sc.nextLine();
        System.out.println("Enter customer age: ");
        int age = sc.nextInt();
        sc.nextLine();

        String flatDetail = updateSeat(flat, seat);
        Passenger passenger = new Passenger(name, age, email, seat.toUpperCase(), bookingID());
        flights.get(flat - 1).getPassengers().add(passenger);
        System.out.println("Hi " + name + ", " + flatDetail + seat.toUpperCase() + ",\nand " +
                "your booking number is " + passenger.getBookingNum() + "\n");
    }

    public void isBookCancel(String searchValue) {

        String flightName = getFlightName(flights, searchValue);
        while ((flightName == null)) {
            System.out.println("Invalid value. Please enter a search value");
            searchValue = sc.nextLine();

            flightName = getFlightName(flights, searchValue);
        }
        updateCancelSeat(flightName, getPassSeatNum(flights, searchValue));
    }

    public void printPassengerByFlight() {
        for (Flight flight : flights) {
            System.out.println(("List Of Passenger On " + flight.flightNameAndNum()).toUpperCase()
            + "\n===========================================");
            for (int i = 0; i < flight.getPassengers().size(); i++) {
                System.out.println(flight.getPassengers().get(i).listPassanger());
            }
            if (flight.getPassengers().size() == 0) {
                System.out.println("There are no passenger on airline " + flight.getFlightNum());
            }
            System.out.println();
        }
    }

    public void printSeat(int position) {
        Flight flight = flights.get(position - 1);
        System.out.println(("List of seats number on " + flight.getFlightName() + " " +
                "" + flight.getFlightNum()).toUpperCase() + "\n" +
                "========================================");
        for (List<String> seat : flight.getSeats()) {
            for (int i = 0; i < seat.size(); i++) {
                if (i != 0) {
                    System.out.print(seat.get(i) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String createSeat(String name) {

        List<List<String>> seats = flights.get(indexOf(name)).getSeats();

        System.out.println("PICK AN AVAILABLE SEAT FROM THE LIST\n" +
                "===================================");
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j <seats.get(i).size(); j++) {
                if (j != 0) {
                    System.out.print(seats.get(i).get(j) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nEnter a seat number (X mean the seat is taken): ");
        String seat = sc.nextLine();
        while (seat.equalsIgnoreCase("X") || !isSeatAvailable(flights, (indexOf(name) + 1), seat)) {
            System.out.println("Invalid. Enter a seat number (Ex: K3): ");
            seat = sc.nextLine();
        }
        return seat;
    }

    private void isSeatUpdated(String flightName, String seat) {
        System.out.println("Enter customer name: ");
        String name = sc.nextLine();
        System.out.println("Enter customer email: ");
        String email = sc.nextLine();
        System.out.println("Enter customer age: ");
        int age = sc.nextInt();
        sc.nextLine();

        String flatDetail = updateSeat(flightName, seat);
        Passenger passenger = new Passenger(name, age, email, seat.toUpperCase(), bookingID());
        flights.get(indexOf(flightName)).getPassengers().add(passenger);
        System.out.println("Hi " + name + ", " + flatDetail + seat.toUpperCase() + ",\nand " +
                "your booking number is " + passenger.getBookingNum() + "\n");
    }

    private String updateSeat(int flat, String seat) {

        List<List<String>> seats = flights.get(flat - 1).getSeats();
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).size(); j++) {
                if (seats.get(i).get(j).equalsIgnoreCase(seat)) {
                    seats.get(i).set(j, "X");
                    break;
                }
            }
        }
        return (flights.get(flat - 1).flightDetail());
    }

    private String updateSeat(String flightName, String seat) {
        List<List<String>> seats = flights.get(indexOf(flightName)).getSeats();
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).size(); j++) {
                if (seats.get(i).get(j).equalsIgnoreCase(seat)) {
                    seats.get(i).set(j, "X");
                    break;
                }
            }
        }
        return (flights.get(indexOf(flightName)).flightDetail());
    }

    private void updateCancelSeat(String flightName, String seatNum) {

        List<List<String>> seats = flights.get((indexOf(flightName))).getSeats();
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).size(); j++) {
                if ((seats.get(i).get(j).charAt(0) == seatNum.charAt(0))) {
                    if (j != 0) {
                        int rowNum = Integer.parseInt(seats.get(i).get(j).substring(1));
                        int customerSeatNum = Integer.parseInt(seatNum.substring(1));
                        if ((customerSeatNum - rowNum) == 1) {
                            seats.get(i).set((rowNum + 1), seatNum);
                            System.out.println("Your booking have been successfully cancel!");
                        }
                    }
                }

            }
        }
    }

    private Flight availableFlight(List<Flight> flights, String flightName) {
        for (Flight flight : flights) {
            if (flight.getFlightName().toUpperCase().contains(flightName.toUpperCase())) {
                System.out.println(flight.toString() + " is available");
                return flight;
            }
        }
        return null;
    }

    public void loadFlat() {
        flights.add(new Flight("United Airline", airlineSeat.is80SeatCreated()));
        flights.add(new Flight("Delta", airlineSeat.is64SeatCreated()));
        flights.add(new Flight("Emirates", airlineSeat.is120SeatCreated()));
    }

    private int bookingID() {
        Random random = new Random();
        return (1000 + random.nextInt( 90000));
    }

    private String getPassSeatNum(List<Flight> flights, String searchValue) {
        for (Flight flight : flights) {
            for (Passenger p : flight.getPassengers()) {
                if ((p.getFullName().equalsIgnoreCase(searchValue)) ||
                        p.getBookingNum().equalsIgnoreCase(searchValue)) {
                    return p.getSeatNum();
                }
            }
        }
        return null;
    }

    private String getFlightName(List<Flight> flights, String searchValue) {

        for (Flight flight : flights) {
            for (Passenger p : flight.getPassengers()) {
                if ((p.getFullName().equalsIgnoreCase(searchValue)) ||
                        p.getBookingNum().equalsIgnoreCase(searchValue)) {
                    return flight.getFlightName();
                }
            }
        }
        return null;
    }

    private int indexOf(String value) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightName().equalsIgnoreCase(value)) {
                return (flights.indexOf(flights.get(i)));
            }
        }
        return -1;
    }

    private boolean isSeatAvailable(List<Flight> flight, int flightPos, String seatNum) {
        Flight f = flight.get(flightPos - 1);
        for (int i = 0; i < f.getSeats().size(); i++) {
            List<String> seat = f.getSeats().get(i);
            for (String s : seat) {
                if (s.equalsIgnoreCase(seatNum)) {
                    return true;
                }
            }
        }
        return false;
    }

}
