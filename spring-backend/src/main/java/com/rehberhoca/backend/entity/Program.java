package com.rehberhoca.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "programlar")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aciklama", columnDefinition = "TEXT")
    private String aciklama;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "aktif")
    private Integer aktif;

    @Column(name = "baslangic_tarihi")
    private LocalDateTime baslangicTarihi;

    @Column(name = "bitis_tarihi")
    private LocalDateTime bitisTarihi;

    @Column(name = "durum")
    private String durum;

    @Column(name = "kapasite")
    private Integer kapasite;

    @Column(name = "kategori")
    private String kategori;

    @Column(name = "olusturma_tarihi")
    private LocalDateTime olusturmaTarihi;

    @Column(name = "seviye")
    private String seviye;

    @Column(name = "sure")
    private Integer sure;

    // Default constructor
    public Program() {}

    // Constructor
    public Program(String ad, String aciklama, String kategori, String seviye, Integer sure, Integer kapasite) {
        this.ad = ad;
        this.aciklama = aciklama;
        this.kategori = kategori;
        this.seviye = seviye;
        this.sure = sure;
        this.kapasite = kapasite;
        this.aktif = 1;
        this.durum = "Aktif";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getAktif() {
        return aktif;
    }

    public void setAktif(Integer aktif) {
        this.aktif = aktif;
    }

    public LocalDateTime getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(LocalDateTime baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public LocalDateTime getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(LocalDateTime bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Integer getKapasite() {
        return kapasite;
    }

    public void setKapasite(Integer kapasite) {
        this.kapasite = kapasite;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public String getSeviye() {
        return seviye;
    }

    public void setSeviye(String seviye) {
        this.seviye = seviye;
    }

    public Integer getSure() {
        return sure;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", kategori='" + kategori + '\'' +
                ", seviye='" + seviye + '\'' +
                ", sure=" + sure +
                ", aktif=" + aktif +
                '}';
    }
}
