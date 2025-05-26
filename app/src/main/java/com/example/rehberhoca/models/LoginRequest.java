package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    private String email;
    
    @SerializedName("sifre")
    private String sifre;

    // Default constructor
    public LoginRequest() {}

    // Constructor with fields
    public LoginRequest(String email, String sifre) {
        this.email = email;
        this.sifre = sifre;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", sifre='[HIDDEN]'" +
                '}';
    }
}
