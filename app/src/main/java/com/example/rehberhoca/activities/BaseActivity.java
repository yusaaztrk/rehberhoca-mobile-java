package com.example.rehberhoca.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rehberhoca.R;
import com.example.rehberhoca.network.NetworkUtils;
import com.example.rehberhoca.utils.SharedPrefsManager;

public abstract class BaseActivity extends AppCompatActivity {
    
    protected SharedPrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsManager = new SharedPrefsManager(this);
    }

    /**
     * Show toast message
     * @param message Message to show
     */
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show long toast message
     * @param message Message to show
     */
    protected void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Check if network is available
     * @return true if network is available
     */
    protected boolean isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailable(this);
    }

    /**
     * Show network error dialog
     */
    protected void showNetworkError() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.error_network)
                .setMessage("İnternet bağlantınızı kontrol edin ve tekrar deneyin.")
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    /**
     * Show error dialog with custom message
     * @param message Error message
     */
    protected void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Hata")
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    /**
     * Show confirmation dialog
     * @param title Dialog title
     * @param message Dialog message
     * @param positiveButtonText Positive button text
     * @param negativeButtonText Negative button text
     * @param listener Click listener
     */
    protected void showConfirmationDialog(String title, String message, 
                                        String positiveButtonText, String negativeButtonText,
                                        DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, listener)
                .setNegativeButton(negativeButtonText, null)
                .show();
    }

    /**
     * Show loading dialog (can be overridden by subclasses)
     * @param show true to show, false to hide
     */
    protected void showLoading(boolean show) {
        // Default implementation - can be overridden
    }

    /**
     * Handle API error response
     * @param throwable Error throwable
     */
    protected void handleApiError(Throwable throwable) {
        String errorMessage = NetworkUtils.getErrorMessage(throwable);
        showErrorDialog(errorMessage);
    }

    /**
     * Get SharedPreferences manager
     * @return SharedPrefsManager instance
     */
    protected SharedPrefsManager getPrefsManager() {
        return prefsManager;
    }
}
