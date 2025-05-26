package com.rehberhoca.backend.repository;

import com.rehberhoca.backend.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    
    /**
     * Aktif programları getir
     */
    @Query("SELECT p FROM Program p WHERE p.aktif = true")
    List<Program> findAllActive();
    
    /**
     * Kategoriye göre programları getir
     */
    List<Program> findByKategoriAndAktifTrue(String kategori);
    
    /**
     * Zorluk seviyesine göre programları getir
     */
    List<Program> findByZorlukSeviyesiAndAktifTrue(String zorlukSeviyesi);
    
    /**
     * Program adına göre arama
     */
    @Query("SELECT p FROM Program p WHERE p.programAdi LIKE %:programAdi% AND p.aktif = true")
    List<Program> findByProgramAdiContainingAndAktifTrue(@Param("programAdi") String programAdi);
    
    /**
     * Öğrenci ID'sine göre atanmış programları getir (JOIN ile)
     */
    @Query("SELECT p FROM Program p " +
           "INNER JOIN OgrenciProgramAtamasi opa ON p.id = opa.programId " +
           "WHERE opa.ogrenciId = :ogrenciId AND p.aktif = true")
    List<Program> findProgramsByOgrenciId(@Param("ogrenciId") Long ogrenciId);
    
    /**
     * Öğrenci ID'sine göre aktif atanmış programları getir
     */
    @Query("SELECT p FROM Program p " +
           "INNER JOIN OgrenciProgramAtamasi opa ON p.id = opa.programId " +
           "WHERE opa.ogrenciId = :ogrenciId AND opa.durum = 'Aktif' AND p.aktif = true")
    List<Program> findActiveProgramsByOgrenciId(@Param("ogrenciId") Long ogrenciId);
}
