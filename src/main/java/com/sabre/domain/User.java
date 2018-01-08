package com.sabre.domain;

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
    private double miles;
    private LocalDate birthDate;
    private String username;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String email, double miles, LocalDate birthDate,
                String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.miles = miles;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, double miles, LocalDate birthDate, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.miles = miles;
        this.birthDate = birthDate;
        this.username = username;
    }

    public User(String firstName, String lastName, String email, double miles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.miles = miles;
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

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
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
}
