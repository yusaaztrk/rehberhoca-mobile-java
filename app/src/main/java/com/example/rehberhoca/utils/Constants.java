package com.example.rehberhoca.utils;

public class Constants {
    
    // SharedPreferences Keys
    public static final String PREF_NAME = "RehberHocaMobile";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String KEY_STUDENT_ID = "studentId";
    public static final String KEY_STUDENT_NAME = "studentName";
    public static final String KEY_STUDENT_EMAIL = "studentEmail";
    public static final String KEY_STUDENT_PHONE = "studentPhone";
    public static final String KEY_AUTH_TOKEN = "authToken";
    
    // Request Codes
    public static final int REQUEST_CODE_LOGIN = 1001;
    
    // Intent Extra Keys
    public static final String EXTRA_STUDENT_ID = "extra_student_id";
    public static final String EXTRA_COURSE_ID = "extra_course_id";
    
    // Network Configuration
    public static final int NETWORK_TIMEOUT = 30; // seconds
    public static final int MAX_RETRY_COUNT = 3;
    
    // UI Configuration
    public static final int SPLASH_DELAY = 2000; // milliseconds
    public static final int ANIMATION_DURATION = 300; // milliseconds
    
    // Validation Constants
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 50;
    
    // Date Format
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    
    // Error Messages
    public static final String ERROR_NETWORK_UNAVAILABLE = "İnternet bağlantısı yok";
    public static final String ERROR_SERVER_UNREACHABLE = "Sunucuya ulaşılamıyor";
    public static final String ERROR_UNKNOWN = "Bilinmeyen hata oluştu";
    
    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Constants class cannot be instantiated");
    }
}
