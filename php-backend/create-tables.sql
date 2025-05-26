-- MySQL tabloları oluştur
CREATE DATABASE IF NOT EXISTS rehberhoca_db;
USE rehberhoca_db;

-- Öğrenciler tablosu
CREATE TABLE IF NOT EXISTS ogrenciler (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ad_soyad VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    sifre VARCHAR(255) NOT NULL,
    telefon VARCHAR(20),
    kayit_tarihi DATETIME DEFAULT CURRENT_TIMESTAMP,
    aktif BOOLEAN DEFAULT TRUE
);

-- Öğrenci program atamaları tablosu
CREATE TABLE IF NOT EXISTS ogrenci_program_atamalari (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ogrenci_id INT NOT NULL,
    program_id INT NOT NULL,
    atama_tarihi DATETIME DEFAULT CURRENT_TIMESTAMP,
    durum VARCHAR(50) DEFAULT 'Aktif',
    notlar TEXT,
    olusturan VARCHAR(255),
    guncelleme_tarihi DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ogrenci_id) REFERENCES ogrenciler(id)
);

-- Test verisi ekle
INSERT IGNORE INTO ogrenciler (id, ad_soyad, email, sifre, telefon) VALUES 
(1, 'Ahmet Yılmaz', 'ahmet.yilmaz@email.com', '123456', '0532 123 4567'),
(2, 'Ayşe Demir', 'ayse.demir@email.com', '123456', '0533 234 5678'),
(3, 'Fatma Öz', 'fatma.oz@email.com', '123456', '0535 456 7890');

-- Test program atamaları
INSERT IGNORE INTO ogrenci_program_atamalari (id, ogrenci_id, program_id, durum, notlar, olusturan) VALUES 
(1, 1, 1, 'Aktif', 'Matematik programı', 'Sistem'),
(2, 1, 3, 'Aktif', 'Fizik programı', 'Sistem'),
(3, 2, 2, 'Aktif', 'Kimya programı', 'Sistem');
