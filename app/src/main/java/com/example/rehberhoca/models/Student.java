package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public class Student {
    @SerializedName("id")
    private Long id;

    @SerializedName("ad_soyad")
    private String adSoyad;

    @SerializedName("email")
    private String email;

    @SerializedName("telefon")
    private String telefon;

    @SerializedName("kayit_tarihi")
    private String kayitTarihi; // Using String for easier JSON parsing

    @SerializedName("aktif")
    private Boolean aktif;

    // Default constructor
    public Student() {}

    // Constructor with all fields
    public Student(Long id, String adSoyad, String email, String telefon, String kayitTarihi, Boolean aktif) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.email = email;
        this.telefon = telefon;
        this.kayitTarihi = kayitTarihi;
        this.aktif = aktif;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getKayitTarihi() {
        return kayitTarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setKayitTarihi(String kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", adSoyad='" + adSoyad + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", kayitTarihi='" + kayitTarihi + '\'' +
                ", aktif=" + aktif +
                '}';
    }
}
