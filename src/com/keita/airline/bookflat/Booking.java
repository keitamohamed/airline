package com.keita.airline.bookflat;

import com.keita.airline.airline.Flight;
import com.keita.airline.flightpassanger.Passenger;
import com.keita.airline.seat.AirLineSeat;

import java.util.*;

public class Booking {

    private List<Flight> flights;
    private Scanner sc = new Scanner(System.in);

    private AirLineSeat airlineSeat;

    public Booking() {
        this.flights = new ArrayList<>();
        this.airlineSeat = new AirLineSeat();
    }

    public void loadFlat() {
        flights.add(new Flight("United Airline", airLineNum(), airlineSeat.is80SeatCreated()));
        flights.add(new Flight("Delta", airLineNum(), airlineSeat.is64SeatCreated()));
        flights.add(new Flight("Emirates", airLineNum(), airlineSeat.is120SeatCreated()));
    }

    public void addNewFlight() {
        System.out.println("Enter name of flight: ");
        String flightName = sc.nextLine();
        flightName = Character.toUpperCase(flightName.charAt(0)) + flightName.substring(1);

        while (flightExist(flights, flightName)) {
            System.out.println("This flight already exist. Enter different name (Yes/No): ");
            String yeaNo = sc.nextLine();

            while (!yeaNo.equalsIgnoreCase("yes") &&
                    !yeaNo.equalsIgnoreCase("no")) {
                System.out.println("Invalid input. Enter different name (Yes/No): ");
                yeaNo = sc.nextLine();
            }
            if (yeaNo.equalsIgnoreCase("no")) {
                return;
            }

            System.out.println("Enter name of flight: ");
            flightName = sc.nextLine();
            flightName = Character.toUpperCase(flightName.charAt(0)) + flightName.substring(1);
        }
        flights.add(new Flight(flightName, airLineNum(), createFlightSeat()));
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

    public String getFlightAndPrint(int flat) {

        List<List<String>> seats = flights.get(flat - 1).getSeats();
        sc.nextLine();

        System.out.println(("LIST OF AVAILABLE SEAT ON " +
                "" + flights.get(flat -1).getFlightName()).toUpperCase() + "\n" +
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
                String seat = getFlightAndPrint(flightName);
                isSeatUpdated(flightName, seat);
            }
        }
    }

    public void isSeatUpdated(int flat, String seat) {
        System.out.println("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.println("Enter passenger email: ");
        String email = sc.nextLine();
        System.out.println("Enter passenger age: ");
        int age = sc.nextInt();
        sc.nextLine();

        String flatDetail = updateSeat(flat, seat);
        Passenger passenger = new Passenger(name, age, email, seat.toUpperCase(), bookingID());
        flights.get(flat - 1).getPassengers().add(passenger);
        System.out.println("Hi " + name + ", " + flatDetail + seat.toUpperCase() + ",\nand " +
                "your booking number is " + passenger.getBookingNum() + "\n");
    }

    public void checkBookingDetail() {
        System.out.println("Enter search value (By: passenger name or booking number): ");
        String searchValue = sc.nextLine();
         while (!isBookingDetailFound(flights, searchValue)) {
             System.out.println("There are no booking detail by the name or booking number of " + searchValue + ".\n" +
                     "Would you like to try another search value (Yes/No): ");
             String tryAgain = sc.nextLine();
             while (!tryAgain.equalsIgnoreCase("Yes") &&
                     !tryAgain.equalsIgnoreCase("No")) {
                 System.out.println("Invalid input. Would you like to try another search value (Yes/No): ");
                 tryAgain = sc.nextLine();
             }
             if (tryAgain.equalsIgnoreCase("Yes")) {
                 System.out.println("Enter search value (By: passenger name or booking number): ");
                 searchValue = sc.nextLine();
             }
             else {
                 return;
             }
         }
    }

    public void isBookCancel(String searchValue) {

        String flightName = getFlightName(flights, searchValue);
        while ((flightName == null)) {
            System.out.println("Invalid value. Please enter a search value");
            searchValue = sc.nextLine();

            flightName = getFlightName(flights, searchValue);
        }
        if (getFlight(flights, searchValue) != null) {
            updateCancelSeat(flightName, getPassSeatNum(flights, searchValue));
            deletePassenger(getFlight(flights, searchValue), searchValue);
        }
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

    public void printFlightSeat(int position) {
        Flight flight = flights.get(position - 1);
        System.out.println(("List of seats number on " + flight.getFlightName() + " " +
                "" + flight.getFlightNum()).toUpperCase() + "\n" +
                "========================================");
        for (List<String> seat : flight.getSeats()) {
            for (int i = 0; i < seat.size(); i++) {
                if (0 < i) {
                    System.out.print(seat.get(i) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFlightSeat() {
        for (Flight flight : flights) {
            System.out.println(("\nList of seats number on " + flight.getFlightName() + " " +
                    "" + flight.getFlightNum()).toUpperCase() + "\n" +
                    "========================================");
            for (List<String> s : flight.getSeats()) {
                for (int i = 0; i < s.size(); i++) {
                    if (i > 0) {
                        System.out.print(s.get(i) + " ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    private List<List<String>> createFlightSeat() {
        System.out.println(("Select number of passengers flight can hold\n" +
                "========================================").toUpperCase() + "\n" +
                "1. 64 passengers\n" +
                "2. 80 passengers\n" +
                "3. 120 passengers\n" +
                "Select one choice: ");
        int choice = sc.nextInt();

        while (choice < 1 || choice > 3) {
            System.out.println("Invalid input. Select one choice");
            choice = sc.nextInt();
        }
        if (choice == 1) {
            return airlineSeat.is64SeatCreated();
        }
        else if (choice == 2) {
            return airlineSeat.is80SeatCreated();
        }
        else {
            return airlineSeat.is120SeatCreated();
        }
    }

    private String getFlightAndPrint(String name) {

        List<List<String>> seats = flights.get(indexOf(name)).getSeats();
        System.out.println(("LIST OF AVAILABLE SEAT ON " +
                        "" + flights.get(indexOf(name))).toUpperCase() + "\n" +
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
        System.out.println("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.println("Enter passenger email: ");
        String email = sc.nextLine();
        System.out.println("Enter passenger age: ");
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
        for (List<String> s : seats) {
            for (int j = 0; j < s.size(); j++) {
                if (s.get(j).equalsIgnoreCase(seat)) {
                    s.set(j, "X");
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

    private boolean isBookingDetailFound(List<Flight> flights, String searchValue) {
        for (Flight flight : flights) {
            List<Passenger> passengers = flight.getPassengers();
            for (Passenger p : passengers) {
                if (p.getFullName().equalsIgnoreCase(searchValue)) {
                    System.out.println(p.getFullName() + " book a flight on " + flight.getFlightName() + "\n");
                    return true;
                }else if (p.getBookingNum().equalsIgnoreCase(searchValue)) {
                    System.out.println(p.bookingDetail((flight.getFlightName() + " " + flight.getFlightNum())));
                    return true;
                }
            }
        }
        return false;
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

    private int bookingID() {
        Random random = new Random();
        return (1000 + random.nextInt( 90000));
    }

    private int airLineNum() {
        Random random = new Random();
        return (1000 + random.nextInt(9000));
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

    private void deletePassenger(Flight flight, String searchValue) {
        for (int i = 0; i < flight.getPassengers().size(); i++) {
            if ((flight.getPassengers().get(i).getFullName().equalsIgnoreCase(searchValue)) ||
                    flight.getPassengers().get(i).getBookingNum().equalsIgnoreCase(searchValue)) {
                flight.getPassengers().remove(flight.getPassengers().get(i));
            }
        }
    }

    private Flight getFlight(List<Flight> flights, String searchValue) {
        for (Flight flight : flights) {
            List<Passenger> passenger = flight.getPassengers();
            for (int i = 0; i < passenger.size(); i++) {
                if (passenger.get(i).getFullName().equalsIgnoreCase(searchValue) ||
                        passenger.get(i).getBookingNum().equalsIgnoreCase(searchValue)) {
                    return flight;
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

    private boolean flightExist(List<Flight> flights, String flightName) {
        for (Flight flight: flights) {
            if (flight.getFlightName().equalsIgnoreCase(flightName)) {
                return true;
            }
        }
        return false;
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
