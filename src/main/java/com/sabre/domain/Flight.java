package com.sabre.domain;

import java.util.Calendar;

public class Flight {
    private long flightId;
    private String userEmail;
    private String airportDepartureCode;
    private String airportArrivalCode;
    private String airlineCode;
    private double miles;
    private FlightClass flightClass;
    private boolean returnTicket;
    private Calendar departureFlightDate;
    private Calendar returnFlightlDate;

    public Flight() {
    }

    public Flight(long id, String userEmail, long miles, String airportDepartureCode, String airportArrivalCode ) {
        this.flightId = id;
        this.userEmail = userEmail;
        this.miles = miles;
        this.airportDepartureCode = airportDepartureCode;
        this.airportArrivalCode = airportArrivalCode;
    }

    public Flight(String userEmail, String airportDepartureCode, String airportArrivalCode, String airlineCode,
                  double miles, FlightClass flightClass, boolean returnTicket, Calendar departureFlightDate,
                  Calendar returnFlightlDate) {
        this.userEmail = userEmail;
        this.airportDepartureCode = airportDepartureCode;
        this.airportArrivalCode = airportArrivalCode;
        this.airlineCode = airlineCode;
        this.miles = miles;
        this.flightClass = flightClass;
        this.returnTicket = returnTicket;
        this.departureFlightDate = departureFlightDate;
        this.returnFlightlDate = returnFlightlDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAirportDepartureCode() {
        return airportDepartureCode;
    }

    public void setAirportDepartureCode(String airportDepartureCode) {
        this.airportDepartureCode = airportDepartureCode;
    }

    public String getAirportArrivalCode() {
        return airportArrivalCode;
    }

    public void setAirportArrivalCode(String airportArrivalCode) {
        this.airportArrivalCode = airportArrivalCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public boolean isReturnTicket() {
        return returnTicket;
    }

    public void setReturnTicket(boolean returnTicket) {
        this.returnTicket = returnTicket;
    }

    public Calendar getDepartureFlightDate() {
        return departureFlightDate;
    }

    public void setDepartureFlightDate(Calendar departureFlightDate) {
        this.departureFlightDate = departureFlightDate;
    }

    public Calendar getReturnFlightlDate() {
        return returnFlightlDate;
    }

    public void setReturnFlightlDate(Calendar returnFlightlDate) {
        this.returnFlightlDate = returnFlightlDate;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "userEmail='" + userEmail + '\'' +
                ", airportDepartureCode='" + airportDepartureCode + '\'' +
                ", airportArrivalCode='" + airportArrivalCode + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", miles=" + miles +
                ", flightClass=" + flightClass +
                ", returnTicket=" + returnTicket +
                ", departureFlightDate=" + departureFlightDate +
                ", returnFlightlDate=" + returnFlightlDate +
                '}';
    }
}
