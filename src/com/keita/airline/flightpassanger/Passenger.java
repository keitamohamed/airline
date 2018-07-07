package com.keita.airline.flightpassanger;

public class Passenger {
    private String fullName;
    private String email;
    private int age;
    private String seatNum;
    private String bookingNum;


    public Passenger() {

    }

    public Passenger(String n, int a, String address, String fNum, int bookingID) {
        this.fullName = n;
        this.age = a;
        this.email = address;
        this.seatNum = fNum;
        this.bookingNum = "BO" + bookingID + "AL";
    }

    @Override
    public String toString() {
        return "Customer name: " + fullName + " email " + email + " " + "age " + age;
    }

    public String listPassanger() {
        return "Name: " + fullName + ", booking #: " + bookingNum + ", seat #: " + seatNum;
    }

    public String bookingDetail(String flightName) {
        return fullName + " book a flight on " + flightName + ", seat number " + seatNum + "\n";
    }

    public String seatDetail() {
        return ", seat " + seatNum;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public String getBookingNum() {
        return bookingNum;
    }
}
