package com.keita.airline.airline;

import java.util.List;

public class United {
    private String fullName;
    private String email;
    private String orderNum;
    private String seatNum;
    private List<List<String>> listSeat;

    private final String airLineNum = "UNAL";
    private final String[] seatRow = {"A", "B", "C", "D", "E", "F", "G", "H"};

    private int num = 0016;

    public United() {

    }

    public United(String n, String e, String seat) {
        this.fullName = n;
        this.email = e;
        this.seatNum = seat;
        orderNum = airLineNum + num;
        num++;
    }

    public United (List<List<String>> seat) {
        this.listSeat = seat;
    }

    @Override
    public String toString() {
        return "Hi " + fullName + "\nThank for choosing United Airline as your flat service.\n" +
                "Your flat number is " + orderNum + " and your seat number is " + seatNum;
    }

//    public List<List<String>> isCreater() {
//        if (createSeat().size() == 0){
//            return createSeat();
//        }
//        return createSeat();
//    }

//    private List<List<String>> createSeat() {
//
//        listSeat = new ArrayList<>();
//        for (int i = 0; i < seatRow.length; i++) {
//            listSeat.add(new ArrayList<>());
//            listSeat.get(i).add(seatRow[i]);
//            for (int j = 1; j <= seatRow.length; j++) {
//                for (int k = 1; k <= 2; k++) {
//                    listSeat.get(i).add(seatRow[j-1] + Integer.toString((k)));
//                }
//            }
//        }
//        return listSeat;
//    }

    public List<List<String>> getListSeat() {
        return listSeat;
    }
}
