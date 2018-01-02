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
    private String nickname;

    public User() {
    }

    public User(String firstName, String lastName, String email, double miles, LocalDate birthDate, String nickname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.miles = miles;
        this.birthDate = birthDate;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
