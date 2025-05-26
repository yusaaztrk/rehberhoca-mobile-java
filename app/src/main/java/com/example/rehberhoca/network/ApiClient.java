package com.example.rehberhoca.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // Base URL for the API - Apache Server
    private static final String BASE_URL = "http://10.0.2.2/rehberhoca/"; // Android Emulator

    // Alternative URLs for different environments
    // For physical device on same network: "http://192.168.1.XXX/rehberhoca/"
    // For localhost XAMPP: "http://localhost/rehberhoca/"
    // For production: "https://your-domain.com/rehberhoca/"

    private static Retrofit retrofit;
    private static ApiService apiService;

    /**
     * Get Retrofit instance with proper configuration
     * @return Configured Retrofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Create HTTP logging interceptor for debugging
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Configure OkHttp client
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();

            // Build Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get API service instance
     * @return ApiService instance for making API calls
     */
    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofitInstance().create(ApiService.class);
        }
        return apiService;
    }

    /**
     * Reset the client (useful for testing or changing base URL)
     */
    public static void resetClient() {
        retrofit = null;
        apiService = null;
    }
}
