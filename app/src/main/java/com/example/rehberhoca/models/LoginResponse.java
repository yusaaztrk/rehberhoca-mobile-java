package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("student")
    private Student student;
    
    @SerializedName("token")
    private String token; // JWT token (optional)

    // Default constructor
    public LoginResponse() {}

    // Constructor with all fields
    public LoginResponse(boolean success, String message, Student student, String token) {
        this.success = success;
        this.message = message;
        this.student = student;
        this.token = token;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Student getStudent() {
        return student;
    }

    public String getToken() {
        return token;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", student=" + student +
                ", token='" + (token != null ? "[HIDDEN]" : "null") + '\'' +
                '}';
    }
}
