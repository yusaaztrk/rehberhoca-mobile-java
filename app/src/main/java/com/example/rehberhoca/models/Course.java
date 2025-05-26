package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    private Long id;

    @SerializedName("ad")
    private String ad;

    @SerializedName("aciklama")
    private String aciklama;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("seviye")
    private String seviye;

    @SerializedName("sure")
    private Integer sure;

    @SerializedName("kapasite")
    private Integer kapasite;

    @SerializedName("durum")
    private String durum;

    @SerializedName("baslangicTarihi")
    private String baslangicTarihi;

    @SerializedName("bitisTarihi")
    private String bitisTarihi;

    @SerializedName("olusturmaTarihi")
    private String olusturmaTarihi;

    @SerializedName("aktif")
    private Integer aktif;

    // Default constructor
    public Course() {}

    // Constructor with all fields
    public Course(Long id, String ad, String aciklama, String kategori, String seviye,
                  Integer sure, Integer kapasite, String durum, String baslangicTarihi,
                  String bitisTarihi, String olusturmaTarihi, Integer aktif) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
        this.kategori = kategori;
        this.seviye = seviye;
        this.sure = sure;
        this.kapasite = kapasite;
        this.durum = durum;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.olusturmaTarihi = olusturmaTarihi;
        this.aktif = aktif;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getSeviye() {
        return seviye;
    }

    public Integer getSure() {
        return sure;
    }

    public Integer getKapasite() {
        return kapasite;
    }

    public String getDurum() {
        return durum;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public String getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public Integer getAktif() {
        return aktif;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setSeviye(String seviye) {
        this.seviye = seviye;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    public void setKapasite(Integer kapasite) {
        this.kapasite = kapasite;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public void setOlusturmaTarihi(String olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public void setAktif(Integer aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", kategori='" + kategori + '\'' +
                ", seviye='" + seviye + '\'' +
                ", sure=" + sure +
                ", aktif=" + aktif +
                '}';
    }
}
