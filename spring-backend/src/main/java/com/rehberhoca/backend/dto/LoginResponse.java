package com.rehberhoca.backend.dto;

import com.rehberhoca.backend.entity.OgrenciProgramAtamasi;

import java.util.List;

public class LoginResponse {
    
    private boolean success;
    private String message;
    private StudentDto student;
    private List<OgrenciProgramAtamasi> programs;
    private int programCount;

    // Default constructor
    public LoginResponse() {}

    // Constructor for success
    public LoginResponse(boolean success, String message, StudentDto student, List<OgrenciProgramAtamasi> programs) {
        this.success = success;
        this.message = message;
        this.student = student;
        this.programs = programs;
        this.programCount = programs != null ? programs.size() : 0;
    }

    // Constructor for error
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.programCount = 0;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public List<OgrenciProgramAtamasi> getPrograms() {
        return programs;
    }

    public void setPrograms(List<OgrenciProgramAtamasi> programs) {
        this.programs = programs;
        this.programCount = programs != null ? programs.size() : 0;
    }

    public int getProgramCount() {
        return programCount;
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
                ", programCount=" + programCount +
                '}';
    }
}
