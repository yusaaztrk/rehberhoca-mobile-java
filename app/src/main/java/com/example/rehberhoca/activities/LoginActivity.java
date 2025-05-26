package com.example.rehberhoca.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rehberhoca.R;
import com.example.rehberhoca.models.LoginRequest;
import com.example.rehberhoca.models.LoginResponse;
import com.example.rehberhoca.network.ApiClient;
import com.example.rehberhoca.network.ApiService;
import com.example.rehberhoca.network.NetworkUtils;
import com.example.rehberhoca.utils.ValidationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    // UI Components
    private EditText etEmailInput;
    private EditText etPasswordInput;
    private Button btnLogin;
    private TextView tvErrorMessage;
    private TextView tvForgotPassword;
    private ProgressBar pbLoginLoading;

    // Network
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Check if already logged in
        if (prefsManager.isLoggedIn()) {
            navigateToDashboard();
            return;
        }

        initViews();
        setupClickListeners();
        initNetwork();
    }

    /**
     * Initialize UI components
     */
    private void initViews() {
        etEmailInput = findViewById(R.id.et_email_input);
        etPasswordInput = findViewById(R.id.et_password_input);
        btnLogin = findViewById(R.id.btn_login);
        tvErrorMessage = findViewById(R.id.tv_error_message);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        pbLoginLoading = findViewById(R.id.pb_login_loading);
    }

    /**
     * Setup click listeners
     */
    private void setupClickListeners() {
        btnLogin.setOnClickListener(v -> performLogin());

        tvForgotPassword.setOnClickListener(v -> {
            // TODO: Implement forgot password functionality
            showToast("Şifre sıfırlama özelliği yakında eklenecek");
        });
    }

    /**
     * Initialize network components
     */
    private void initNetwork() {
        apiService = ApiClient.getApiService();
    }

    /**
     * Perform login operation
     */
    private void performLogin() {
        String email = etEmailInput.getText().toString().trim();
        String password = etPasswordInput.getText().toString().trim();

        // Validate inputs
        if (!validateInputs(email, password)) {
            return;
        }

        // Check network connectivity
        if (!isNetworkAvailable()) {
            showNetworkError();
            return;
        }

        // Show loading state
        showLoading(true);

        // Create login request
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Make API call
        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                showLoading(false);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess() && loginResponse.getStudent() != null) {
                        // Login successful
                        prefsManager.saveLoginSession(loginResponse.getStudent());
                        if (loginResponse.getToken() != null) {
                            prefsManager.saveAuthToken(loginResponse.getToken());
                        }

                        // Show program count if available
                        String successMessage = "Giriş başarılı";
                        if (loginResponse.getPrograms() != null && loginResponse.getProgramCount() > 0) {
                            successMessage += " - " + loginResponse.getProgramCount() + " program bulundu";
                        }

                        showToast(successMessage);
                        navigateToDashboard();
                    } else {
                        // Login failed
                        String errorMessage = loginResponse.getMessage() != null ?
                                loginResponse.getMessage() : getString(R.string.error_login_failed);
                        showError(errorMessage);
                    }
                } else {
                    // HTTP error
                    String errorMessage = NetworkUtils.getErrorMessage(response);
                    showError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showLoading(false);
                String errorMessage = NetworkUtils.getErrorMessage(t);
                showError(errorMessage);
            }
        });
    }

    /**
     * Validate user inputs
     * @param email Email address
     * @param password Password
     * @return true if valid, false otherwise
     */
    private boolean validateInputs(String email, String password) {
        // Validate email
        String emailError = ValidationUtils.getEmailError(email);
        if (emailError != null) {
            showError(emailError);
            etEmailInput.requestFocus();
            return false;
        }

        // Validate password
        String passwordError = ValidationUtils.getPasswordError(password);
        if (passwordError != null) {
            showError(passwordError);
            etPasswordInput.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Show loading state
     * @param show true to show loading, false to hide
     */
    @Override
    protected void showLoading(boolean show) {
        pbLoginLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        btnLogin.setEnabled(!show);
        btnLogin.setText(show ? R.string.login_loading : R.string.login_button);

        // Hide error message when loading
        if (show) {
            hideError();
        }
    }

    /**
     * Show error message
     * @param message Error message to display
     */
    private void showError(String message) {
        tvErrorMessage.setText(message);
        tvErrorMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Hide error message
     */
    private void hideError() {
        tvErrorMessage.setVisibility(View.GONE);
    }

    /**
     * Navigate to dashboard activity
     */
    private void navigateToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
