package com.rehberhoca.backend.controller;

import com.rehberhoca.backend.dto.LoginRequest;
import com.rehberhoca.backend.dto.LoginResponse;
import com.rehberhoca.backend.dto.ProgramDto;
import com.rehberhoca.backend.entity.OgrenciProgramAtamasi;
import com.rehberhoca.backend.service.OgrenciService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private OgrenciService ogrenciService;

    /**
     * Test endpoint
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Spring Boot Backend çalışıyor!");
        response.put("timestamp", java.time.LocalDateTime.now());
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }

    /**
     * Öğrenci girişi
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Login request: " + loginRequest);

        LoginResponse response = ogrenciService.login(loginRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    /**
     * Öğrenci programlarını getir (Detaylı - Program bilgileri ile birlikte)
     */
    @GetMapping("/student/{studentId}/programs")
    public ResponseEntity<Map<String, Object>> getStudentPrograms(@PathVariable Long studentId) {
        try {
            List<ProgramDto> programs = ogrenciService.getStudentProgramsDetailed(studentId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", programs);
            response.put("count", programs.size());
            response.put("student_id", studentId);
            response.put("message", "Öğrenci programları başarıyla getirildi");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Programlar getirilirken hata oluştu: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Öğrenci aktif programlarını getir
     */
    @GetMapping("/student/{studentId}/programs/active")
    public ResponseEntity<Map<String, Object>> getStudentActivePrograms(@PathVariable Long studentId) {
        try {
            List<OgrenciProgramAtamasi> programs = ogrenciService.getStudentActivePrograms(studentId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", programs);
            response.put("count", programs.size());
            response.put("student_id", studentId);
            response.put("active_only", true);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Aktif programlar getirilirken hata oluştu: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
