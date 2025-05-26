package com.example.rehberhoca.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehberhoca.R;
import com.example.rehberhoca.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> coursesList;
    private OnCourseClickListener clickListener;

    public CoursesAdapter() {
        this.coursesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CoursesAdapter", "🏗️ onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        Log.d("CoursesAdapter", "✅ ViewHolder created successfully");
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Log.d("CoursesAdapter", "🔗 onBindViewHolder called for position: " + position);

        if (position >= coursesList.size()) {
            Log.e("CoursesAdapter", "❌ Invalid position: " + position + ", size: " + coursesList.size());
            return;
        }

        Course course = coursesList.get(position);
        Log.d("CoursesAdapter", "📖 Binding course: " + course.getAd());
        bindCourseData(holder, course);
        Log.d("CoursesAdapter", "✅ Course bound successfully at position: " + position);
    }

    @Override
    public int getItemCount() {
        int count = coursesList.size();
        Log.d("CoursesAdapter", "getItemCount() returning: " + count);
        return count;
    }

    /**
     * Update courses list - Clean Implementation
     * @param newCourses New list of courses
     */
    public void updateCourses(List<Course> newCourses) {
        Log.d("CoursesAdapter", "🔄 updateCourses called with: " +
              (newCourses != null ? newCourses.size() : "null") + " courses");

        // Clear existing data
        int oldSize = coursesList.size();
        coursesList.clear();

        // Add new data
        if (newCourses != null && !newCourses.isEmpty()) {
            coursesList.addAll(newCourses);
            Log.d("CoursesAdapter", "✅ Added " + newCourses.size() + " courses to adapter");

            // Log first few courses for debugging
            int logCount = Math.min(3, newCourses.size());
            for (int i = 0; i < logCount; i++) {
                Log.d("CoursesAdapter", "📖 Course " + (i+1) + ": " + newCourses.get(i).getAd());
            }
            if (newCourses.size() > 3) {
                Log.d("CoursesAdapter", "... and " + (newCourses.size() - 3) + " more courses");
            }
        } else {
            Log.d("CoursesAdapter", "⚠️ No courses to add");
        }

        Log.d("CoursesAdapter", "📊 Final coursesList size: " + coursesList.size());

        // Notify adapter
        notifyDataSetChanged();
        Log.d("CoursesAdapter", "🔔 notifyDataSetChanged() called");
    }

    /**
     * Add courses to existing list
     * @param newCourses Courses to add
     */
    public void addCourses(List<Course> newCourses) {
        if (newCourses != null && !newCourses.isEmpty()) {
            int startPosition = coursesList.size();
            coursesList.addAll(newCourses);
            notifyItemRangeInserted(startPosition, newCourses.size());
        }
    }

    /**
     * Clear all courses
     */
    public void clearCourses() {
        coursesList.clear();
        notifyDataSetChanged();
    }

    /**
     * Get course at position
     * @param position Position in list
     * @return Course object or null
     */
    public Course getCourseAt(int position) {
        if (position >= 0 && position < coursesList.size()) {
            return coursesList.get(position);
        }
        return null;
    }

    /**
     * Set click listener
     * @param listener Click listener
     */
    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.clickListener = listener;
    }

    /**
     * Bind course data to view holder
     * @param holder View holder
     * @param course Course data
     */
    private void bindCourseData(CourseViewHolder holder, Course course) {
        // Set course name
        holder.tvCourseName.setText(course.getAd() != null ? course.getAd() : "");

        // Set course category
        if (course.getKategori() != null && !course.getKategori().isEmpty()) {
            holder.tvCourseCategory.setText(course.getKategori().toUpperCase());
            holder.tvCourseCategory.setVisibility(View.VISIBLE);

            // Set category color indicator
            int categoryColor = getCategoryColor(course.getKategori(), holder.itemView.getContext());
            holder.vCategoryIndicator.setBackgroundColor(categoryColor);
        } else {
            holder.tvCourseCategory.setVisibility(View.GONE);
        }

        // Set course description
        if (course.getAciklama() != null && !course.getAciklama().isEmpty()) {
            holder.tvCourseDescription.setText(course.getAciklama());
            holder.tvCourseDescription.setVisibility(View.VISIBLE);
        } else {
            holder.tvCourseDescription.setVisibility(View.GONE);
        }

        // Set course duration
        if (course.getSure() != null && course.getSure() > 0) {
            holder.tvCourseDuration.setText(course.getSure() + " hafta");
        } else {
            holder.tvCourseDuration.setText("Süre belirtilmemiş");
        }

        // Set course level
        if (course.getSeviye() != null && !course.getSeviye().isEmpty()) {
            holder.tvCourseLevel.setText(course.getSeviye());
        } else {
            holder.tvCourseLevel.setText("Seviye belirtilmemiş");
        }

        // Set course status
        if (course.getAktif() != null && course.getAktif() == 1) {
            holder.tvCourseStatus.setText("Aktif");
            holder.tvCourseStatus.setBackgroundColor(
                holder.itemView.getContext().getColor(R.color.success_container));
            holder.tvCourseStatus.setTextColor(
                holder.itemView.getContext().getColor(R.color.success_color));
        } else {
            holder.tvCourseStatus.setText("Pasif");
            holder.tvCourseStatus.setBackgroundColor(
                holder.itemView.getContext().getColor(R.color.outline_variant));
            holder.tvCourseStatus.setTextColor(
                holder.itemView.getContext().getColor(R.color.text_hint));
        }

        // Set action button text
        holder.tvCourseSchedule.setText("Devam Et");

        // Set click listener
        holder.cvCourseContainer.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onCourseClick(course, holder.getAdapterPosition());
            }
        });
    }

    /**
     * Get category color based on category name
     * @param category Category name
     * @param context Context for color resources
     * @return Color value
     */
    private int getCategoryColor(String category, android.content.Context context) {
        if (category == null) return context.getColor(R.color.category_general);

        String categoryLower = category.toLowerCase();
        if (categoryLower.contains("programlama") || categoryLower.contains("yazılım")) {
            return context.getColor(R.color.category_programming);
        } else if (categoryLower.contains("tasarım") || categoryLower.contains("design")) {
            return context.getColor(R.color.category_design);
        } else if (categoryLower.contains("veri") || categoryLower.contains("data")) {
            return context.getColor(R.color.category_data);
        } else if (categoryLower.contains("web")) {
            return context.getColor(R.color.category_web);
        } else if (categoryLower.contains("mobil") || categoryLower.contains("mobile")) {
            return context.getColor(R.color.category_mobile);
        } else {
            return context.getColor(R.color.category_general);
        }
    }

    /**
     * ViewHolder class for course items - Modern UI
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        CardView cvCourseContainer;
        TextView tvCourseName;
        TextView tvCourseDescription;
        TextView tvCourseCategory;
        TextView tvCourseStatus;
        TextView tvCourseDuration;
        TextView tvCourseLevel;
        TextView tvCourseSchedule;
        ImageView ivCourseIcon;
        View vCategoryIndicator;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCourseContainer = itemView.findViewById(R.id.cv_course_container);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCourseDescription = itemView.findViewById(R.id.tv_course_description);
            tvCourseCategory = itemView.findViewById(R.id.tv_course_category);
            tvCourseStatus = itemView.findViewById(R.id.tv_course_status);
            tvCourseDuration = itemView.findViewById(R.id.tv_course_duration);
            tvCourseLevel = itemView.findViewById(R.id.tv_course_level);
            tvCourseSchedule = itemView.findViewById(R.id.tv_course_schedule);
            ivCourseIcon = itemView.findViewById(R.id.iv_course_icon);
            vCategoryIndicator = itemView.findViewById(R.id.v_category_indicator);
        }
    }

    /**
     * Interface for course click events
     */
    public interface OnCourseClickListener {
        void onCourseClick(Course course, int position);
    }
}
