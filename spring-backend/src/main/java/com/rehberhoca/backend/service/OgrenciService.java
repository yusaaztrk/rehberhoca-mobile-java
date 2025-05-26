package com.rehberhoca.backend.service;

import com.rehberhoca.backend.dto.LoginRequest;
import com.rehberhoca.backend.dto.LoginResponse;
import com.rehberhoca.backend.dto.StudentDto;
import com.rehberhoca.backend.dto.ProgramDto;
import com.rehberhoca.backend.entity.Ogrenci;
import com.rehberhoca.backend.entity.OgrenciProgramAtamasi;
import com.rehberhoca.backend.entity.Program;
import com.rehberhoca.backend.repository.OgrenciRepository;
import com.rehberhoca.backend.repository.OgrenciProgramAtamasiRepository;
import com.rehberhoca.backend.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OgrenciService {

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Autowired
    private OgrenciProgramAtamasiRepository programAtamasiRepository;

    @Autowired
    private ProgramRepository programRepository;

    /**
     * Öğrenci girişi
     */
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Email ve şifre ile öğrenci ara
            Optional<Ogrenci> ogrenciOpt = ogrenciRepository.findByEmailAndSifre(
                    loginRequest.getEmail(),
                    loginRequest.getSifre()
            );

            if (ogrenciOpt.isPresent()) {
                Ogrenci ogrenci = ogrenciOpt.get();

                // Öğrenci programlarını getir
                List<OgrenciProgramAtamasi> programs = programAtamasiRepository.findByOgrenciId(ogrenci.getId());

                // StudentDto oluştur
                StudentDto studentDto = new StudentDto(ogrenci);

                return new LoginResponse(true, "Giriş başarılı", studentDto, programs);
            } else {
                return new LoginResponse(false, "Email veya şifre hatalı");
            }
        } catch (Exception e) {
            return new LoginResponse(false, "Giriş işlemi sırasında hata oluştu: " + e.getMessage());
        }
    }

    /**
     * Öğrenci ID'sine göre öğrenci getir
     */
    public Optional<Ogrenci> getById(Long id) {
        return ogrenciRepository.findById(id);
    }

    /**
     * Email'e göre öğrenci getir
     */
    public Optional<Ogrenci> getByEmail(String email) {
        return ogrenciRepository.findByEmail(email);
    }

    /**
     * Tüm aktif öğrencileri getir
     */
    public List<Ogrenci> getAllActive() {
        return ogrenciRepository.findAllActive();
    }

    /**
     * Öğrenci programlarını getir
     */
    public List<OgrenciProgramAtamasi> getStudentPrograms(Long ogrenciId) {
        return programAtamasiRepository.findByOgrenciId(ogrenciId);
    }

    /**
     * Öğrenci aktif programlarını getir
     */
    public List<OgrenciProgramAtamasi> getStudentActivePrograms(Long ogrenciId) {
        return programAtamasiRepository.findActiveByOgrenciId(ogrenciId);
    }

    /**
     * Öğrenci programlarını detaylı olarak getir (Program bilgileri ile birlikte)
     */
    public List<ProgramDto> getStudentProgramsDetailed(Long ogrenciId) {
        List<Program> programs = programRepository.findProgramsByOgrenciId(ogrenciId);
        return programs.stream()
                .map(ProgramDto::new)
                .toList();
    }

    /**
     * Öğrenci aktif programlarını detaylı olarak getir
     */
    public List<ProgramDto> getStudentActiveProgramsDetailed(Long ogrenciId) {
        List<Program> programs = programRepository.findActiveProgramsByOgrenciId(ogrenciId);
        return programs.stream()
                .map(ProgramDto::new)
                .toList();
    }
}
