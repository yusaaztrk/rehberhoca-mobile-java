package com.rehberhoca.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "programlar")
public class Program {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "program_adi", nullable = false)
    private String programAdi;
    
    @Column(name = "aciklama", columnDefinition = "TEXT")
    private String aciklama;
    
    @Column(name = "sure_dakika")
    private Integer sureDakika;
    
    @Column(name = "zorluk_seviyesi")
    private String zorlukSeviyesi;
    
    @Column(name = "kategori")
    private String kategori;
    
    @Column(name = "olusturan")
    private String olusturan;
    
    @Column(name = "olusturma_tarihi")
    private LocalDateTime olusturmaTarihi;
    
    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;
    
    @Column(name = "aktif")
    private Boolean aktif = true;

    // Default constructor
    public Program() {}

    // Constructor
    public Program(String programAdi, String aciklama, Integer sureDakika, String zorlukSeviyesi, String kategori, String olusturan) {
        this.programAdi = programAdi;
        this.aciklama = aciklama;
        this.sureDakika = sureDakika;
        this.zorlukSeviyesi = zorlukSeviyesi;
        this.kategori = kategori;
        this.olusturan = olusturan;
        this.olusturmaTarihi = LocalDateTime.now();
        this.guncellemeTarihi = LocalDateTime.now();
        this.aktif = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramAdi() {
        return programAdi;
    }

    public void setProgramAdi(String programAdi) {
        this.programAdi = programAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getSureDakika() {
        return sureDakika;
    }

    public void setSureDakika(Integer sureDakika) {
        this.sureDakika = sureDakika;
    }

    public String getZorlukSeviyesi() {
        return zorlukSeviyesi;
    }

    public void setZorlukSeviyesi(String zorlukSeviyesi) {
        this.zorlukSeviyesi = zorlukSeviyesi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getOlusturan() {
        return olusturan;
    }

    public void setOlusturan(String olusturan) {
        this.olusturan = olusturan;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public LocalDateTime getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(LocalDateTime guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", programAdi='" + programAdi + '\'' +
                ", kategori='" + kategori + '\'' +
                ", zorlukSeviyesi='" + zorlukSeviyesi + '\'' +
                ", sureDakika=" + sureDakika +
                ", aktif=" + aktif +
                '}';
    }
}
