package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    private Long id;
    
    @SerializedName("ad")
    private String ad;
    
    @SerializedName("aciklama")
    private String aciklama;
    
    @SerializedName("egitmen")
    private String egitmen;
    
    @SerializedName("program")
    private String program;
    
    @SerializedName("baslangicTarihi")
    private String baslangicTarihi; // Using String for easier JSON parsing
    
    @SerializedName("bitisTarihi")
    private String bitisTarihi; // Using String for easier JSON parsing
    
    @SerializedName("aktif")
    private Boolean aktif;

    // Default constructor
    public Course() {}

    // Constructor with all fields
    public Course(Long id, String ad, String aciklama, String egitmen, String program, 
                  String baslangicTarihi, String bitisTarihi, Boolean aktif) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
        this.egitmen = egitmen;
        this.program = program;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
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

    public String getEgitmen() {
        return egitmen;
    }

    public String getProgram() {
        return program;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public Boolean getAktif() {
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

    public void setEgitmen(String egitmen) {
        this.egitmen = egitmen;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", egitmen='" + egitmen + '\'' +
                ", program='" + program + '\'' +
                ", baslangicTarihi='" + baslangicTarihi + '\'' +
                ", bitisTarihi='" + bitisTarihi + '\'' +
                ", aktif=" + aktif +
                '}';
    }
}
