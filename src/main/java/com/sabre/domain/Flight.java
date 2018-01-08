package com.sabre.domain;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Entity
@Table(name = "Flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long flightId;

    private String userEmail;
    private String airportDepartureCode;
    private String airportArrivalCode;
    private String airlineCode;
    private double miles;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;
    private boolean returnTicket;
    private LocalDate departureFlightDate;
    private LocalDate returnFlightDate;

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
                  double miles, FlightClass flightClass, boolean returnTicket, LocalDate departureFlightDate,
                  LocalDate returnFlightDate) {
        this.userEmail = userEmail;
        this.airportDepartureCode = airportDepartureCode;
        this.airportArrivalCode = airportArrivalCode;
        this.airlineCode = airlineCode;
        this.miles = miles;
        this.flightClass = flightClass;
        this.returnTicket = returnTicket;
        this.departureFlightDate = departureFlightDate;
        this.returnFlightDate = returnFlightDate;
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

    public LocalDate getDepartureFlightDate() {
        return departureFlightDate;
    }

    public void setDepartureFlightDate(LocalDate departureFlightDate) {
        this.departureFlightDate = departureFlightDate;
    }

    public LocalDate getReturnFlightDate() {
        return returnFlightDate;
    }

    @JsonSetter
    public void setReturnFlightDate(long returnFlightDate) {
        this.returnFlightDate = Instant.ofEpochMilli(returnFlightDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonSetter
    public void setDepartureFlightDate(long departureFlightDate) {
        this.departureFlightDate = Instant.ofEpochMilli(departureFlightDate).atZone(ZoneId.systemDefault()).toLocalDate();
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
                ", returnFlightDate=" + returnFlightDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return flightId == flight.flightId &&
                Double.compare(flight.miles, miles) == 0 &&
                returnTicket == flight.returnTicket &&
                Objects.equals(userEmail, flight.userEmail) &&
                Objects.equals(airportDepartureCode, flight.airportDepartureCode) &&
                Objects.equals(airportArrivalCode, flight.airportArrivalCode) &&
                Objects.equals(airlineCode, flight.airlineCode) &&
                flightClass == flight.flightClass &&
                Objects.equals(departureFlightDate, flight.departureFlightDate) &&
                Objects.equals(returnFlightDate, flight.returnFlightDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, userEmail, airportDepartureCode, airportArrivalCode, airlineCode, miles, flightClass, returnTicket, departureFlightDate, returnFlightDate);
    }
}
