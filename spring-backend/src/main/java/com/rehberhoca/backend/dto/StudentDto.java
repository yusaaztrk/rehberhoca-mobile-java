package com.rehberhoca.backend.dto;

import com.rehberhoca.backend.entity.Ogrenci;

import java.time.LocalDateTime;

public class StudentDto {
    
    private Long id;
    private String ad_soyad;
    private String email;
    private String telefon;
    private LocalDateTime kayit_tarihi;
    private Boolean aktif;

    // Default constructor
    public StudentDto() {}

    // Constructor from Entity
    public StudentDto(Ogrenci ogrenci) {
        this.id = ogrenci.getId();
        this.ad_soyad = ogrenci.getAdSoyad();
        this.email = ogrenci.getEmail();
        this.telefon = ogrenci.getTelefon();
        this.kayit_tarihi = ogrenci.getKayitTarihi();
        this.aktif = ogrenci.getAktif();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDateTime getKayit_tarihi() {
        return kayit_tarihi;
    }

    public void setKayit_tarihi(LocalDateTime kayit_tarihi) {
        this.kayit_tarihi = kayit_tarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", ad_soyad='" + ad_soyad + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", aktif=" + aktif +
                '}';
    }
}
