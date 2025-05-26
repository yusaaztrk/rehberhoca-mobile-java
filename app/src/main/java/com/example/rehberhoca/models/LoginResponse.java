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

    @SerializedName("programs")
    private java.util.List<StudentProgram> programs; // Student programs

    @SerializedName("program_count")
    private int programCount; // Number of programs

    // Default constructor
    public LoginResponse() {}

    // Constructor with all fields
    public LoginResponse(boolean success, String message, Student student, String token) {
        this.success = success;
        this.message = message;
        this.student = student;
        this.token = token;
    }

    // Constructor with programs
    public LoginResponse(boolean success, String message, Student student, String token,
                        java.util.List<StudentProgram> programs, int programCount) {
        this.success = success;
        this.message = message;
        this.student = student;
        this.token = token;
        this.programs = programs;
        this.programCount = programCount;
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

    public java.util.List<StudentProgram> getPrograms() {
        return programs;
    }

    public int getProgramCount() {
        return programCount;
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

    public void setPrograms(java.util.List<StudentProgram> programs) {
        this.programs = programs;
    }

    public void setProgramCount(int programCount) {
        this.programCount = programCount;
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
