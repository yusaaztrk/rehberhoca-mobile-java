package com.rehberhoca.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ogrenciler")
public class Ogrenci {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ad_soyad", nullable = false)
    private String adSoyad;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "sifre", nullable = false)
    private String sifre;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "kayit_tarihi")
    private LocalDateTime kayitTarihi;
    
    @Column(name = "aktif")
    private Boolean aktif = true;

    // Default constructor
    public Ogrenci() {}

    // Constructor
    public Ogrenci(String adSoyad, String email, String sifre, String telefon) {
        this.adSoyad = adSoyad;
        this.email = email;
        this.sifre = sifre;
        this.telefon = telefon;
        this.kayitTarihi = LocalDateTime.now();
        this.aktif = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDateTime getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(LocalDateTime kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "id=" + id +
                ", adSoyad='" + adSoyad + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", aktif=" + aktif +
                '}';
    }
}
