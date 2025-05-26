package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * StudentProgramsResponse Model
 * Response model for student programs API endpoint
 */
public class StudentProgramsResponse {
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("data")
    private List<StudentProgram> data;
    
    @SerializedName("count")
    private int count;
    
    @SerializedName("student_id")
    private Long studentId;
    
    @SerializedName("active_only")
    private boolean activeOnly;

    // Default constructor
    public StudentProgramsResponse() {}

    // Constructor with all fields
    public StudentProgramsResponse(boolean success, String message, List<StudentProgram> data, 
                                  int count, Long studentId, boolean activeOnly) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
        this.studentId = studentId;
        this.activeOnly = activeOnly;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<StudentProgram> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public Long getStudentId() {
        return studentId;
    }

    public boolean isActiveOnly() {
        return activeOnly;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<StudentProgram> data) {
        this.data = data;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setActiveOnly(boolean activeOnly) {
        this.activeOnly = activeOnly;
    }

    @Override
    public String toString() {
        return "StudentProgramsResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", studentId=" + studentId +
                ", activeOnly=" + activeOnly +
                '}';
    }
}
