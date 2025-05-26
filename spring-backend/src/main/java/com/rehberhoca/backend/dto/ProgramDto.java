package com.rehberhoca.backend.dto;

import com.rehberhoca.backend.entity.Program;

import java.time.LocalDateTime;

public class ProgramDto {

    private Long id;
    private String aciklama;
    private String ad;
    private Integer aktif;
    private LocalDateTime baslangicTarihi;
    private LocalDateTime bitisTarihi;
    private String durum;
    private Integer kapasite;
    private String kategori;
    private LocalDateTime olusturmaTarihi;
    private String seviye;
    private Integer sure;

    // Default constructor
    public ProgramDto() {}

    // Constructor from Entity
    public ProgramDto(Program program) {
        this.id = program.getId();
        this.aciklama = program.getAciklama();
        this.ad = program.getAd();
        this.aktif = program.getAktif();
        this.baslangicTarihi = program.getBaslangicTarihi();
        this.bitisTarihi = program.getBitisTarihi();
        this.durum = program.getDurum();
        this.kapasite = program.getKapasite();
        this.kategori = program.getKategori();
        this.olusturmaTarihi = program.getOlusturmaTarihi();
        this.seviye = program.getSeviye();
        this.sure = program.getSure();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
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
        return "ProgramDto{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", kategori='" + kategori + '\'' +
                ", seviye='" + seviye + '\'' +
                ", sure=" + sure +
                ", aktif=" + aktif +
                '}';
    }
}
