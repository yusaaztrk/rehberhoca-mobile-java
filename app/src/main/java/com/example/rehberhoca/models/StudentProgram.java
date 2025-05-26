package com.example.rehberhoca.models;

import com.google.gson.annotations.SerializedName;

/**
 * StudentProgram Model
 * Represents a program assignment for a student
 */
public class StudentProgram {
    @SerializedName("id")
    private Long id;
    
    @SerializedName("ogrenci_id")
    private Long ogrenciId;
    
    @SerializedName("program_id")
    private Long programId;
    
    @SerializedName("atama_tarihi")
    private String atamaTarihi;
    
    @SerializedName("durum")
    private String durum;
    
    @SerializedName("notlar")
    private String notlar;
    
    @SerializedName("olusturan")
    private String olusturan;
    
    @SerializedName("guncelleme_tarihi")
    private String guncellemeTarihi;

    // Default constructor
    public StudentProgram() {}

    // Constructor with all fields
    public StudentProgram(Long id, Long ogrenciId, Long programId, String atamaTarihi, 
                         String durum, String notlar, String olusturan, String guncellemeTarihi) {
        this.id = id;
        this.ogrenciId = ogrenciId;
        this.programId = programId;
        this.atamaTarihi = atamaTarihi;
        this.durum = durum;
        this.notlar = notlar;
        this.olusturan = olusturan;
        this.guncellemeTarihi = guncellemeTarihi;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getOgrenciId() {
        return ogrenciId;
    }

    public Long getProgramId() {
        return programId;
    }

    public String getAtamaTarihi() {
        return atamaTarihi;
    }

    public String getDurum() {
        return durum;
    }

    public String getNotlar() {
        return notlar;
    }

    public String getOlusturan() {
        return olusturan;
    }

    public String getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setOgrenciId(Long ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public void setAtamaTarihi(String atamaTarihi) {
        this.atamaTarihi = atamaTarihi;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public void setNotlar(String notlar) {
        this.notlar = notlar;
    }

    public void setOlusturan(String olusturan) {
        this.olusturan = olusturan;
    }

    public void setGuncellemeTarihi(String guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    // Utility methods
    public boolean isActive() {
        return "Aktif".equals(durum);
    }

    @Override
    public String toString() {
        return "StudentProgram{" +
                "id=" + id +
                ", ogrenciId=" + ogrenciId +
                ", programId=" + programId +
                ", atamaTarihi='" + atamaTarihi + '\'' +
                ", durum='" + durum + '\'' +
                ", notlar='" + notlar + '\'' +
                ", olusturan='" + olusturan + '\'' +
                ", guncellemeTarihi='" + guncellemeTarihi + '\'' +
                '}';
    }
}
