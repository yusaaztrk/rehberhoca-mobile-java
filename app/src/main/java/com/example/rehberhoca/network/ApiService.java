package com.example.rehberhoca.network;

import com.example.rehberhoca.models.Course;
import com.example.rehberhoca.models.LoginRequest;
import com.example.rehberhoca.models.LoginResponse;
import com.example.rehberhoca.models.Student;
import com.example.rehberhoca.models.StudentProgramsResponse;

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
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    /**
     * Get programs assigned to a specific student
     * @param studentId The ID of the student
     * @return List of programs assigned to the student
     */
    @GET("auth/student/{studentId}/programs")
    Call<StudentProgramsResponse> getStudentPrograms(@Path("studentId") Long studentId);

    /**
     * Get student profile information
     * @param studentId The ID of the student
     * @return Student profile data
     */
    @GET("api/mobile/student/profile/{studentId}")
    Call<Student> getStudentProfile(@Path("studentId") Long studentId);
}
