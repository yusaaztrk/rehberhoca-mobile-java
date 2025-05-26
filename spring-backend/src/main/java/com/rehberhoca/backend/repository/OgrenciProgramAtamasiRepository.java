package com.rehberhoca.backend.repository;

import com.rehberhoca.backend.entity.OgrenciProgramAtamasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgrenciProgramAtamasiRepository extends JpaRepository<OgrenciProgramAtamasi, Long> {
    
    /**
     * Öğrenci ID'sine göre program atamalarını getir
     */
    List<OgrenciProgramAtamasi> findByOgrenciId(Long ogrenciId);
    
    /**
     * Öğrenci ID'sine göre aktif program atamalarını getir
     */
    @Query("SELECT opa FROM OgrenciProgramAtamasi opa WHERE opa.ogrenciId = :ogrenciId AND opa.durum = 'Aktif'")
    List<OgrenciProgramAtamasi> findActiveByOgrenciId(@Param("ogrenciId") Long ogrenciId);
    
    /**
     * Program ID'sine göre atamaları getir
     */
    List<OgrenciProgramAtamasi> findByProgramId(Long programId);
    
    /**
     * Öğrenci ve program ID'sine göre atama var mı kontrol et
     */
    boolean existsByOgrenciIdAndProgramId(Long ogrenciId, Long programId);
}
