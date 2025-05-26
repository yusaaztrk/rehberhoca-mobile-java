package com.rehberhoca.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ogrenci_program_atamalari")
public class OgrenciProgramAtamasi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ogrenci_id", nullable = false)
    private Long ogrenciId;
    
    @Column(name = "program_id", nullable = false)
    private Long programId;
    
    @Column(name = "atama_tarihi")
    private LocalDateTime atamaTarihi;
    
    @Column(name = "durum")
    private String durum = "Aktif";
    
    @Column(name = "notlar", columnDefinition = "TEXT")
    private String notlar;
    
    @Column(name = "olusturan")
    private String olusturan;
    
    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;

    // Default constructor
    public OgrenciProgramAtamasi() {}

    // Constructor
    public OgrenciProgramAtamasi(Long ogrenciId, Long programId, String durum, String notlar, String olusturan) {
        this.ogrenciId = ogrenciId;
        this.programId = programId;
        this.durum = durum;
        this.notlar = notlar;
        this.olusturan = olusturan;
        this.atamaTarihi = LocalDateTime.now();
        this.guncellemeTarihi = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(Long ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public LocalDateTime getAtamaTarihi() {
        return atamaTarihi;
    }

    public void setAtamaTarihi(LocalDateTime atamaTarihi) {
        this.atamaTarihi = atamaTarihi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getNotlar() {
        return notlar;
    }

    public void setNotlar(String notlar) {
        this.notlar = notlar;
    }

    public String getOlusturan() {
        return olusturan;
    }

    public void setOlusturan(String olusturan) {
        this.olusturan = olusturan;
    }

    public LocalDateTime getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(LocalDateTime guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    @Override
    public String toString() {
        return "OgrenciProgramAtamasi{" +
                "id=" + id +
                ", ogrenciId=" + ogrenciId +
                ", programId=" + programId +
                ", durum='" + durum + '\'' +
                ", olusturan='" + olusturan + '\'' +
                '}';
    }
}
