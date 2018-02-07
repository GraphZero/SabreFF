package com.sabre.domain;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",  nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private double initialMiles;
    private LocalDate birthDate;
    private String username;
    private String password;

    @Formula(value = "(SELECT SUM( F.miles) FROM Flights F WHERE F.user_email = email )")
    private Long flightsMiles;

    public User() {
    }

    public User(String firstName, String lastName, String email, double initialMiles, LocalDate birthDate,
                String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.initialMiles = initialMiles;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, double initialMiles, LocalDate birthDate, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.initialMiles = initialMiles;
        this.birthDate = birthDate;
        this.username = username;
    }

    public User(String firstName, String lastName, String email, double initialMiles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.initialMiles = initialMiles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getInitialMiles() {
        return initialMiles;
    }

    public void setInitialMiles(double initialMiles) {
        this.initialMiles = initialMiles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getFlightsMiles() {
        return flightsMiles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", initialMiles=" + initialMiles +
                ", birthDate=" + birthDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flightsMiles=" + flightsMiles +
                '}';
    }
}

