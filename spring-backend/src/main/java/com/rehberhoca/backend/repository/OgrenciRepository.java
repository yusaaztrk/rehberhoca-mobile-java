package com.rehberhoca.backend.repository;

import com.rehberhoca.backend.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {
    
    /**
     * Email ile öğrenci bul
     */
    Optional<Ogrenci> findByEmail(String email);
    
    /**
     * Email ve şifre ile öğrenci bul (login için)
     */
    @Query("SELECT o FROM Ogrenci o WHERE o.email = :email AND o.sifre = :sifre AND o.aktif = true")
    Optional<Ogrenci> findByEmailAndSifre(@Param("email") String email, @Param("sifre") String sifre);
    
    /**
     * Email'in var olup olmadığını kontrol et
     */
    boolean existsByEmail(String email);
    
    /**
     * Aktif öğrencileri getir
     */
    @Query("SELECT o FROM Ogrenci o WHERE o.aktif = true")
    java.util.List<Ogrenci> findAllActive();
}
