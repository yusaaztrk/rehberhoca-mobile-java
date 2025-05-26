package com.example.rehberhoca.utils;

import android.util.Patterns;

public class ValidationUtils {
    
    /**
     * Validate email address format
     * @param email Email address to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && !email.trim().isEmpty() && 
               Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }
    
    /**
     * Validate password strength
     * @param password Password to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= Constants.MIN_PASSWORD_LENGTH &&
               password.length() <= Constants.MAX_PASSWORD_LENGTH;
    }
    
    /**
     * Get email validation error message
     * @param email Email to validate
     * @return Error message or null if valid
     */
    public static String getEmailError(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "E-posta adresi gerekli";
        }
        if (!isValidEmail(email)) {
            return "Geçerli bir e-posta adresi girin";
        }
        return null;
    }
    
    /**
     * Get password validation error message
     * @param password Password to validate
     * @return Error message or null if valid
     */
    public static String getPasswordError(String password) {
        if (password == null || password.trim().isEmpty()) {
            return "Şifre gerekli";
        }
        if (password.length() < Constants.MIN_PASSWORD_LENGTH) {
            return "Şifre en az " + Constants.MIN_PASSWORD_LENGTH + " karakter olmalı";
        }
        if (password.length() > Constants.MAX_PASSWORD_LENGTH) {
            return "Şifre en fazla " + Constants.MAX_PASSWORD_LENGTH + " karakter olabilir";
        }
        return null;
    }
    
    /**
     * Check if string is null or empty
     * @param str String to check
     * @return true if null or empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Validate phone number format (Turkish format)
     * @param phone Phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        // Remove spaces and special characters
        String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)]", "");
        // Check Turkish phone number format
        return cleanPhone.matches("^(\\+90|0)?[5][0-9]{9}$");
    }
    
    /**
     * Get phone validation error message
     * @param phone Phone number to validate
     * @return Error message or null if valid
     */
    public static String getPhoneError(String phone) {
        if (isEmpty(phone)) {
            return "Telefon numarası gerekli";
        }
        if (!isValidPhone(phone)) {
            return "Geçerli bir telefon numarası girin";
        }
        return null;
    }
}
