package com.sabre.domain;

/**
 * Created by Andrzej on 2017-10-31.
 */
public class UserEntity {
    private String firstName;
    private String lastName;
    private String email;
    private double miles;
    private long userId;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, double miles, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.miles = miles;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
