package com.example.rehberhoca;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehberhoca.activities.DashboardActivity;
import com.example.rehberhoca.activities.LoginActivity;
import com.example.rehberhoca.utils.Constants;
import com.example.rehberhoca.utils.SharedPrefsManager;

public class MainActivity extends AppCompatActivity {

    private SharedPrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsManager = new SharedPrefsManager(this);

        // Add splash delay and then navigate
        new Handler(Looper.getMainLooper()).postDelayed(this::navigateToNextActivity, Constants.SPLASH_DELAY);
    }

    /**
     * Navigate to appropriate activity based on login status
     */
    private void navigateToNextActivity() {
        Intent intent;

        if (prefsManager.isLoggedIn()) {
            // User is logged in, go to dashboard
            intent = new Intent(this, DashboardActivity.class);
        } else {
            // User is not logged in, go to login
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}