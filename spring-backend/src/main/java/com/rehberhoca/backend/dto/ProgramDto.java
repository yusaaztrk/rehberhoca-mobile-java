package com.rehberhoca.backend.dto;

import com.rehberhoca.backend.entity.Program;

import java.time.LocalDateTime;

public class ProgramDto {
    
    private Long id;
    private String program_adi;
    private String aciklama;
    private Integer sure_dakika;
    private String zorluk_seviyesi;
    private String kategori;
    private String olusturan;
    private LocalDateTime olusturma_tarihi;
    private LocalDateTime guncelleme_tarihi;
    private Boolean aktif;

    // Default constructor
    public ProgramDto() {}

    // Constructor from Entity
    public ProgramDto(Program program) {
        this.id = program.getId();
        this.program_adi = program.getProgramAdi();
        this.aciklama = program.getAciklama();
        this.sure_dakika = program.getSureDakika();
        this.zorluk_seviyesi = program.getZorlukSeviyesi();
        this.kategori = program.getKategori();
        this.olusturan = program.getOlusturan();
        this.olusturma_tarihi = program.getOlusturmaTarihi();
        this.guncelleme_tarihi = program.getGuncellemeTarihi();
        this.aktif = program.getAktif();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgram_adi() {
        return program_adi;
    }

    public void setProgram_adi(String program_adi) {
        this.program_adi = program_adi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Integer getSure_dakika() {
        return sure_dakika;
    }

    public void setSure_dakika(Integer sure_dakika) {
        this.sure_dakika = sure_dakika;
    }

    public String getZorluk_seviyesi() {
        return zorluk_seviyesi;
    }

    public void setZorluk_seviyesi(String zorluk_seviyesi) {
        this.zorluk_seviyesi = zorluk_seviyesi;
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

    public LocalDateTime getOlusturma_tarihi() {
        return olusturma_tarihi;
    }

    public void setOlusturma_tarihi(LocalDateTime olusturma_tarihi) {
        this.olusturma_tarihi = olusturma_tarihi;
    }

    public LocalDateTime getGuncelleme_tarihi() {
        return guncelleme_tarihi;
    }

    public void setGuncelleme_tarihi(LocalDateTime guncelleme_tarihi) {
        this.guncelleme_tarihi = guncelleme_tarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return "ProgramDto{" +
                "id=" + id +
                ", program_adi='" + program_adi + '\'' +
                ", kategori='" + kategori + '\'' +
                ", zorluk_seviyesi='" + zorluk_seviyesi + '\'' +
                ", sure_dakika=" + sure_dakika +
                '}';
    }
}
