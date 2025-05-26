package com.rehberhoca.backend.controller;

import com.rehberhoca.backend.model.Course;
import com.rehberhoca.backend.model.LoginRequest;
import com.rehberhoca.backend.model.LoginResponse;
import com.rehberhoca.backend.model.Student;
import com.rehberhoca.backend.service.OgrenciService;
import com.rehberhoca.backend.service.ProgramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "*")
public class MobileApiController {
    
    @Autowired
    private OgrenciService ogrenciService;
    
    @Autowired
    private ProgramService programService;
    
    /**
     * Student login endpoint
     * @param request LoginRequest containing email and password
     * @return LoginResponse with success status and student data
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            // Validate input
            if (request.getEmail() == null || request.getSifre() == null) {
                LoginResponse response = new LoginResponse();
                response.setSuccess(false);
                response.setMessage("E-posta ve şifre gerekli");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Authenticate student with email and password
            Ogrenci ogrenci = ogrenciService.ogrenciGiris(request.getEmail(), request.getSifre());
            
            if (ogrenci != null && ogrenci.getAktif()) {
                LoginResponse response = new LoginResponse();
                response.setSuccess(true);
                response.setMessage("Giriş başarılı");
                response.setStudent(convertToStudentModel(ogrenci));
                
                // Optional: Generate JWT token
                // String token = jwtTokenUtil.generateToken(ogrenci);
                // response.setToken(token);
                
                return ResponseEntity.ok(response);
            } else {
                LoginResponse response = new LoginResponse();
                response.setSuccess(false);
                response.setMessage("Geçersiz e-posta veya şifre");
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception e) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Giriş işlemi başarısız: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * Get courses assigned to a specific student
     * @param studentId The ID of the student
     * @return List of courses assigned to the student
     */
    @GetMapping("/student/{studentId}/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long studentId) {
        try {
            // Validate student ID
            if (studentId == null || studentId <= 0) {
                return ResponseEntity.badRequest().body(new ArrayList<>());
            }
            
            // Get student's programs/courses
            List<Program> programs = programService.ogrencininProgramlari(studentId);
            
            // Convert to Course model for mobile app
            List<Course> courses = programs.stream()
                    .filter(program -> program.getAktif()) // Only active courses
                    .map(this::convertToCourseModel)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
    }
    
    /**
     * Get student profile information
     * @param studentId The ID of the student
     * @return Student profile data
     */
    @GetMapping("/student/profile/{studentId}")
    public ResponseEntity<Student> getStudentProfile(@PathVariable Long studentId) {
        try {
            // Validate student ID
            if (studentId == null || studentId <= 0) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Get student by ID
            Ogrenci ogrenci = ogrenciService.getOgrenciById(studentId);
            
            if (ogrenci != null) {
                Student student = convertToStudentModel(ogrenci);
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    /**
     * Convert Ogrenci entity to Student model for mobile app
     * @param ogrenci Database entity
     * @return Student model
     */
    private Student convertToStudentModel(Ogrenci ogrenci) {
        Student student = new Student();
        student.setId(ogrenci.getId());
        student.setAdSoyad(ogrenci.getAdSoyad());
        student.setEmail(ogrenci.getEmail());
        student.setTelefon(ogrenci.getTelefon());
        student.setKayitTarihi(ogrenci.getKayitTarihi() != null ? 
                ogrenci.getKayitTarihi().toString() : null);
        student.setAktif(ogrenci.getAktif());
        return student;
    }
    
    /**
     * Convert Program entity to Course model for mobile app
     * @param program Database entity
     * @return Course model
     */
    private Course convertToCourseModel(Program program) {
        Course course = new Course();
        course.setId(program.getId());
        course.setAd(program.getAd());
        course.setAciklama(program.getAciklama());
        course.setEgitmen(program.getEgitmen());
        course.setProgram(program.getKategori());
        course.setBaslangicTarihi(program.getBaslangicTarihi() != null ? 
                program.getBaslangicTarihi().toString() : null);
        course.setBitisTarihi(program.getBitisTarihi() != null ? 
                program.getBitisTarihi().toString() : null);
        course.setAktif(program.getAktif());
        return course;
    }
}
