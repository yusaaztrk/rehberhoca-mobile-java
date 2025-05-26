package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * CoursesResponse Model
 * Response model for student courses API endpoint
 */
public class CoursesResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Course> data;

    @SerializedName("count")
    private int count;

    @SerializedName("studentId")
    private Long studentId;

    // Default constructor
    public CoursesResponse() {}

    // Constructor with all fields
    public CoursesResponse(boolean success, String message, List<Course> data,
                          int count, Long studentId) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
        this.studentId = studentId;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Course> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public Long getStudentId() {
        return studentId;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Course> data) {
        this.data = data;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "CoursesResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", studentId=" + studentId +
                '}';
    }
}
