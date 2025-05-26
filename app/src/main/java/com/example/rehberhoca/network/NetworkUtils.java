package com.example.rehberhoca.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Response;

public class NetworkUtils {
    
    /**
     * Check if device has internet connection
     * @param context Application context
     * @return true if connected, false otherwise
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = 
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
    
    /**
     * Get error message from HTTP response
     * @param response Retrofit response
     * @return Error message string
     */
    public static String getErrorMessage(Response<?> response) {
        if (response == null) {
            return "Bilinmeyen hata";
        }
        
        switch (response.code()) {
            case 400:
                return "Geçersiz istek";
            case 401:
                return "Geçersiz e-posta veya şifre";
            case 403:
                return "Erişim reddedildi";
            case 404:
                return "Kaynak bulunamadı";
            case 500:
                return "Sunucu hatası";
            case 503:
                return "Servis kullanılamıyor";
            default:
                return "Ağ hatası (Kod: " + response.code() + ")";
        }
    }
    
    /**
     * Get error message from throwable
     * @param throwable Exception that occurred
     * @return User-friendly error message
     */
    public static String getErrorMessage(Throwable throwable) {
        if (throwable == null) {
            return "Bilinmeyen hata";
        }
        
        String message = throwable.getMessage();
        if (message == null) {
            return "Ağ bağlantısı hatası";
        }
        
        if (message.contains("timeout")) {
            return "Bağlantı zaman aşımı";
        } else if (message.contains("Unable to resolve host")) {
            return "Sunucuya bağlanılamıyor";
        } else if (message.contains("No address associated with hostname")) {
            return "Ağ bağlantısı yok";
        } else {
            return "Ağ bağlantısı hatası";
        }
    }
}
