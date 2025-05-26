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
    @Query("SELECT p FROM Program p WHERE p.aktif = 1")
    List<Program> findAllActive();

    /**
     * Kategoriye göre programları getir
     */
    @Query("SELECT p FROM Program p WHERE p.kategori = :kategori AND p.aktif = 1")
    List<Program> findByKategoriAndAktif(@Param("kategori") String kategori);

    /**
     * Seviyeye göre programları getir
     */
    @Query("SELECT p FROM Program p WHERE p.seviye = :seviye AND p.aktif = 1")
    List<Program> findBySeviyeAndAktif(@Param("seviye") String seviye);

    /**
     * Program adına göre arama
     */
    @Query("SELECT p FROM Program p WHERE p.ad LIKE %:ad% AND p.aktif = 1")
    List<Program> findByAdContainingAndAktif(@Param("ad") String ad);

    /**
     * Öğrenci ID'sine göre atanmış programları getir (JOIN ile)
     */
    @Query("SELECT p FROM Program p " +
           "INNER JOIN OgrenciProgramAtamasi opa ON p.id = opa.programId " +
           "WHERE opa.ogrenciId = :ogrenciId AND p.aktif = 1")
    List<Program> findProgramsByOgrenciId(@Param("ogrenciId") Long ogrenciId);

    /**
     * Öğrenci ID'sine göre aktif atanmış programları getir
     */
    @Query("SELECT p FROM Program p " +
           "INNER JOIN OgrenciProgramAtamasi opa ON p.id = opa.programId " +
           "WHERE opa.ogrenciId = :ogrenciId AND opa.durum = 'Aktif' AND p.aktif = 1")
    List<Program> findActiveProgramsByOgrenciId(@Param("ogrenciId") Long ogrenciId);
}
