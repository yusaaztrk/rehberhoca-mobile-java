package com.example.rehberhoca.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rehberhoca.models.Student;

public class SharedPrefsManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPrefsManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * Save login session data
     * @param student Student object to save
     */
    public void saveLoginSession(Student student) {
        if (student != null) {
            editor.putBoolean(Constants.KEY_IS_LOGGED_IN, true);
            editor.putLong(Constants.KEY_STUDENT_ID, student.getId() != null ? student.getId() : 0L);
            editor.putString(Constants.KEY_STUDENT_NAME, student.getAdSoyad());
            editor.putString(Constants.KEY_STUDENT_EMAIL, student.getEmail());
            editor.putString(Constants.KEY_STUDENT_PHONE, student.getTelefon());
            editor.apply();
        }
    }

    /**
     * Save authentication token
     * @param token JWT token or session token
     */
    public void saveAuthToken(String token) {
        editor.putString(Constants.KEY_AUTH_TOKEN, token);
        editor.apply();
    }

    /**
     * Check if user is logged in
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Constants.KEY_IS_LOGGED_IN, false);
    }

    /**
     * Get logged in student data
     * @return Student object or null if not logged in
     */
    public Student getLoggedInStudent() {
        if (!isLoggedIn()) {
            return null;
        }

        Student student = new Student();
        student.setId(sharedPreferences.getLong(Constants.KEY_STUDENT_ID, 0L));
        student.setAdSoyad(sharedPreferences.getString(Constants.KEY_STUDENT_NAME, ""));
        student.setEmail(sharedPreferences.getString(Constants.KEY_STUDENT_EMAIL, ""));
        student.setTelefon(sharedPreferences.getString(Constants.KEY_STUDENT_PHONE, ""));
        student.setAktif(true); // Assume active if logged in

        return student;
    }

    /**
     * Get student ID
     * @return Student ID or 0 if not logged in
     */
    public long getStudentId() {
        return sharedPreferences.getLong(Constants.KEY_STUDENT_ID, 0L);
    }

    /**
     * Get student name
     * @return Student name or empty string
     */
    public String getStudentName() {
        return sharedPreferences.getString(Constants.KEY_STUDENT_NAME, "");
    }

    /**
     * Get student email
     * @return Student email or empty string
     */
    public String getStudentEmail() {
        return sharedPreferences.getString(Constants.KEY_STUDENT_EMAIL, "");
    }

    /**
     * Get authentication token
     * @return Auth token or null
     */
    public String getAuthToken() {
        return sharedPreferences.getString(Constants.KEY_AUTH_TOKEN, null);
    }

    /**
     * Logout user and clear session
     */
    public void logout() {
        clearSession();
    }

    /**
     * Clear all session data
     */
    public void clearSession() {
        editor.clear();
        editor.apply();
    }

    /**
     * Update student name
     * @param name New student name
     */
    public void updateStudentName(String name) {
        editor.putString(Constants.KEY_STUDENT_NAME, name);
        editor.apply();
    }
}
