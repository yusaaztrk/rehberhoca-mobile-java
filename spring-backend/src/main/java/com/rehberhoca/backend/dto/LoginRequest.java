package com.rehberhoca.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    
    @NotBlank(message = "Email gerekli")
    @Email(message = "Geçerli bir email adresi girin")
    private String email;
    
    @NotBlank(message = "Şifre gerekli")
    private String sifre;

    // Default constructor
    public LoginRequest() {}

    // Constructor
    public LoginRequest(String email, String sifre) {
        this.email = email;
        this.sifre = sifre;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
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
