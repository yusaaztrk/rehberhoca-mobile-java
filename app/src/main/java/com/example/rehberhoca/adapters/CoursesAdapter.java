package com.example.rehberhoca.adapters;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = coursesList.get(position);
        bindCourseData(holder, course);
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    /**
     * Update courses list
     * @param newCourses New list of courses
     */
    public void updateCourses(List<Course> newCourses) {
        this.coursesList.clear();
        if (newCourses != null) {
            this.coursesList.addAll(newCourses);
        }
        notifyDataSetChanged();
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
        
        // Set course description
        holder.tvCourseDescription.setText(course.getAciklama() != null ? course.getAciklama() : "");
        
        // Set instructor
        String instructorText = holder.itemView.getContext()
                .getString(R.string.course_instructor, 
                    course.getEgitmen() != null ? course.getEgitmen() : "Belirtilmemiş");
        holder.tvCourseInstructor.setText(instructorText);
        
        // Set schedule/program
        String scheduleText = holder.itemView.getContext()
                .getString(R.string.course_schedule, 
                    course.getProgram() != null ? course.getProgram() : "Belirtilmemiş");
        holder.tvCourseSchedule.setText(scheduleText);
        
        // Set click listener
        holder.cvCourseContainer.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onCourseClick(course, holder.getAdapterPosition());
            }
        });
    }

    /**
     * ViewHolder class for course items
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        CardView cvCourseContainer;
        TextView tvCourseName;
        TextView tvCourseDescription;
        TextView tvCourseInstructor;
        TextView tvCourseSchedule;
        ImageView ivCourseIcon;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCourseContainer = itemView.findViewById(R.id.cv_course_container);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCourseDescription = itemView.findViewById(R.id.tv_course_description);
            tvCourseInstructor = itemView.findViewById(R.id.tv_course_instructor);
            tvCourseSchedule = itemView.findViewById(R.id.tv_course_schedule);
            ivCourseIcon = itemView.findViewById(R.id.iv_course_icon);
        }
    }

    /**
     * Interface for course click events
     */
    public interface OnCourseClickListener {
        void onCourseClick(Course course, int position);
    }
}
