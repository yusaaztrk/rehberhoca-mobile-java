package com.example.rehberhoca.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.rehberhoca.R;
import com.example.rehberhoca.adapters.CoursesAdapter;
import com.example.rehberhoca.models.Course;
import com.example.rehberhoca.models.CoursesResponse;
import com.example.rehberhoca.models.Student;
import com.example.rehberhoca.models.StudentProgram;
import com.example.rehberhoca.models.StudentProgramsResponse;
import com.example.rehberhoca.network.ApiClient;
import com.example.rehberhoca.network.ApiService;
import com.example.rehberhoca.network.NetworkUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends BaseActivity implements CoursesAdapter.OnCourseClickListener {

    // UI Components
    private Toolbar toolbarDashboard;
    private TextView tvWelcomeMessage;
    private TextView tvStudentName;
    private RecyclerView rvCoursesList;
    private SwipeRefreshLayout srlCoursesRefresh;
    private ProgressBar pbCoursesLoading;
    private TextView tvNoCoursesMessage;
    private FloatingActionButton fabRefresh;

    // Data & Adapters
    private CoursesAdapter coursesAdapter;
    private Student currentStudent;

    // Network
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Check if user is logged in
        if (!prefsManager.isLoggedIn()) {
            navigateToLogin();
            return;
        }

        initViews();
        setupToolbar();
        setupRecyclerView();
        setupSwipeRefresh();
        setupClickListeners();
        initNetwork();
        loadUserData();
        loadStudentCourses();
    }

    /**
     * Initialize UI components
     */
    private void initViews() {
        toolbarDashboard = findViewById(R.id.toolbar_dashboard);
        tvWelcomeMessage = findViewById(R.id.tv_welcome_message);
        tvStudentName = findViewById(R.id.tv_student_name);
        rvCoursesList = findViewById(R.id.rv_courses_list);
        srlCoursesRefresh = findViewById(R.id.srl_courses_refresh);
        pbCoursesLoading = findViewById(R.id.pb_courses_loading);
        tvNoCoursesMessage = findViewById(R.id.tv_no_courses_message);
        fabRefresh = findViewById(R.id.fab_refresh);
    }

    /**
     * Setup toolbar
     */
    private void setupToolbar() {
        setSupportActionBar(toolbarDashboard);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    /**
     * Setup RecyclerView
     */
    private void setupRecyclerView() {
        coursesAdapter = new CoursesAdapter();
        coursesAdapter.setOnCourseClickListener(this);

        rvCoursesList.setLayoutManager(new LinearLayoutManager(this));
        rvCoursesList.setAdapter(coursesAdapter);
        rvCoursesList.setHasFixedSize(true);
    }

    /**
     * Setup SwipeRefreshLayout
     */
    private void setupSwipeRefresh() {
        srlCoursesRefresh.setColorSchemeResources(
                R.color.primary_color,
                R.color.accent_color
        );
        srlCoursesRefresh.setOnRefreshListener(this::refreshCourses);
    }

    /**
     * Setup click listeners
     */
    private void setupClickListeners() {
        fabRefresh.setOnClickListener(v -> refreshCourses());
    }

    /**
     * Initialize network components
     */
    private void initNetwork() {
        apiService = ApiClient.getApiService();
    }

    /**
     * Load user data from SharedPreferences
     */
    private void loadUserData() {
        currentStudent = prefsManager.getLoggedInStudent();
        if (currentStudent != null) {
            tvStudentName.setText(currentStudent.getAdSoyad());
        }
    }

    /**
     * Load student courses from API - New Clean Implementation
     */
    private void loadStudentCourses() {
        if (currentStudent == null || currentStudent.getId() == null) {
            showError("Öğrenci bilgileri bulunamadı");
            return;
        }

        // Check network connectivity
        if (!isNetworkAvailable()) {
            showNetworkError();
            return;
        }

        Log.d("DashboardActivity", "🔄 Loading courses for student ID: " + currentStudent.getId());
        showLoading(true);

        Call<CoursesResponse> call = apiService.getStudentPrograms(currentStudent.getId());
        Log.d("DashboardActivity", "📡 API URL: " + call.request().url());

        call.enqueue(new Callback<CoursesResponse>() {
            @Override
            public void onResponse(Call<CoursesResponse> call, Response<CoursesResponse> response) {
                handleCoursesResponse(response);
            }

            @Override
            public void onFailure(Call<CoursesResponse> call, Throwable t) {
                handleCoursesFailure(t);
            }
        });
    }

    /**
     * Handle successful API response
     */
    private void handleCoursesResponse(Response<CoursesResponse> response) {
        Log.d("DashboardActivity", "📥 Response Code: " + response.code());
        showLoading(false);
        srlCoursesRefresh.setRefreshing(false);

        if (!response.isSuccessful() || response.body() == null) {
            Log.e("DashboardActivity", "❌ Response not successful or body is null");
            showError("Sunucudan yanıt alınamadı");
            showNoCourses(true);
            return;
        }

        CoursesResponse coursesResponse = response.body();
        Log.d("DashboardActivity", "✅ Response received: " + coursesResponse.toString());

        if (!coursesResponse.isSuccess()) {
            Log.e("DashboardActivity", "❌ API returned success=false");
            showError(coursesResponse.getMessage() != null ?
                     coursesResponse.getMessage() : "Programlar yüklenemedi");
            showNoCourses(true);
            return;
        }

        List<Course> courses = coursesResponse.getData();
        if (courses == null) {
            Log.e("DashboardActivity", "❌ Courses data is null");
            showError("Ders verisi bulunamadı");
            showNoCourses(true);
            return;
        }

        Log.d("DashboardActivity", "📚 Courses loaded: " + courses.size());
        displayCourses(courses);
    }

    /**
     * Handle API failure
     */
    private void handleCoursesFailure(Throwable t) {
        Log.e("DashboardActivity", "💥 API call failed: " + t.getMessage(), t);
        showLoading(false);
        srlCoursesRefresh.setRefreshing(false);
        showError("Bağlantı hatası: " + t.getMessage());
        showNoCourses(true);
    }

    /**
     * Display courses in RecyclerView
     */
    private void displayCourses(List<Course> courses) {
        Log.d("DashboardActivity", "🎯 Displaying " + courses.size() + " courses");

        if (courses.isEmpty()) {
            Log.d("DashboardActivity", "📭 No courses to display");
            showNoCourses(true);
            return;
        }

        // Log each course for debugging
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            Log.d("DashboardActivity", "📖 Course " + (i+1) + ": " + course.getAd());
        }

        // Update adapter
        Log.d("DashboardActivity", "🔄 Updating adapter...");
        coursesAdapter.updateCourses(courses);

        // Show RecyclerView, hide no courses message
        showNoCourses(false);

        Log.d("DashboardActivity", "✅ Courses display completed");
    }



    /**
     * Refresh courses
     */
    private void refreshCourses() {
        loadStudentCourses();
    }

    /**
     * Show/hide loading state
     * @param show true to show loading, false to hide
     */
    @Override
    protected void showLoading(boolean show) {
        pbCoursesLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        if (show) {
            tvNoCoursesMessage.setVisibility(View.GONE);
        }
    }

    /**
     * Show/hide no courses message - Clean Implementation
     * @param show true to show message, false to hide
     */
    private void showNoCourses(boolean show) {
        Log.d("DashboardActivity", "👁️ showNoCourses called with: " + show);

        if (show) {
            // Show "no courses" message, hide RecyclerView
            tvNoCoursesMessage.setVisibility(View.VISIBLE);
            rvCoursesList.setVisibility(View.GONE);
            Log.d("DashboardActivity", "📭 Showing 'no courses' message");
        } else {
            // Hide "no courses" message, show RecyclerView
            tvNoCoursesMessage.setVisibility(View.GONE);
            rvCoursesList.setVisibility(View.VISIBLE);
            Log.d("DashboardActivity", "📚 Showing courses RecyclerView");
        }

        Log.d("DashboardActivity", "✅ UI visibility updated successfully");
    }

    /**
     * Show error message
     * @param message Error message
     */
    private void showError(String message) {
        showErrorDialog(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refreshCourses();
            return true;
        } else if (id == R.id.action_logout) {
            showLogoutConfirmation();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Show logout confirmation dialog
     */
    private void showLogoutConfirmation() {
        showConfirmationDialog(
                getString(R.string.logout_confirmation_title),
                getString(R.string.logout_confirmation_message),
                getString(R.string.logout_yes),
                getString(R.string.logout_no),
                (dialog, which) -> logout()
        );
    }

    /**
     * Logout user
     */
    private void logout() {
        prefsManager.logout();
        navigateToLogin();
    }

    /**
     * Navigate to login activity
     */
    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCourseClick(Course course, int position) {
        // TODO: Implement course detail view
        showToast("Ders detayları: " + course.getAd());
    }
}
