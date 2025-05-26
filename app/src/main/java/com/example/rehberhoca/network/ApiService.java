package com.example.rehberhoca.network;

import com.example.rehberhoca.models.Course;
import com.example.rehberhoca.models.LoginRequest;
import com.example.rehberhoca.models.LoginResponse;
import com.example.rehberhoca.models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    
    /**
     * Student login endpoint
     * @param request LoginRequest containing email and password
     * @return LoginResponse with success status and student data
     */
    @POST("api/mobile/login")
    Call<LoginResponse> login(@Body LoginRequest request);
    
    /**
     * Get courses assigned to a specific student
     * @param studentId The ID of the student
     * @return List of courses assigned to the student
     */
    @GET("api/mobile/student/{studentId}/courses")
    Call<List<Course>> getStudentCourses(@Path("studentId") Long studentId);
    
    /**
     * Get student profile information
     * @param studentId The ID of the student
     * @return Student profile data
     */
    @GET("api/mobile/student/profile/{studentId}")
    Call<Student> getStudentProfile(@Path("studentId") Long studentId);
}
