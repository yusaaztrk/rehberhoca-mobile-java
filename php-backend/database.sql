-- Rehber Hoca Mobile API Veritabanı
-- Bu dosya gerekli tabloları ve test verilerini oluşturur

-- Veritabanı oluştur (eğer yoksa)
CREATE DATABASE IF NOT EXISTS `rehberhoca_db` 
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `rehberhoca_db`;

-- Öğrenciler tablosu
CREATE TABLE IF NOT EXISTS `ogrenciler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adSoyad` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sifre` varchar(255) NOT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `kayitTarihi` datetime DEFAULT CURRENT_TIMESTAMP,
  `aktif` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Programlar tablosu
CREATE TABLE IF NOT EXISTS `programlar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(255) NOT NULL,
  `aciklama` text,
  `egitmen` varchar(255) DEFAULT NULL,
  `kategori` varchar(100) DEFAULT NULL,
  `baslangicTarihi` datetime DEFAULT NULL,
  `bitisTarihi` datetime DEFAULT NULL,
  `aktif` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Öğrenci-Program ilişki tablosu
CREATE TABLE IF NOT EXISTS `ogrenci_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ogrenci_id` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  `kayitTarihi` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ogrenci_id` (`ogrenci_id`),
  KEY `program_id` (`program_id`),
  CONSTRAINT `fk_ogrenci_program_ogrenci` FOREIGN KEY (`ogrenci_id`) REFERENCES `ogrenciler` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ogrenci_program_program` FOREIGN KEY (`program_id`) REFERENCES `programlar` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Test verileri ekle
-- Test öğrencileri
INSERT INTO `ogrenciler` (`adSoyad`, `email`, `sifre`, `telefon`, `aktif`) VALUES
('Test Öğrenci', 'test@rehberhoca.com', '123456', '05551234567', 1),
('Ahmet Yılmaz', 'ahmet@rehberhoca.com', 'ahmet123', '05559876543', 1),
('Ayşe Demir', 'ayse@rehberhoca.com', 'ayse123', '05557654321', 1),
('Mehmet Kaya', 'mehmet@rehberhoca.com', 'mehmet123', '05553456789', 1);

-- Test programları
INSERT INTO `programlar` (`ad`, `aciklama`, `egitmen`, `kategori`, `baslangicTarihi`, `bitisTarihi`, `aktif`) VALUES
('Java Programlama Temelleri', 'Java programlama dilinin temel kavramları ve nesne yönelimli programlama prensipleri', 'Dr. Mehmet Özkan', 'Hafta içi Akşam', '2024-02-01 18:00:00', '2024-04-30 21:00:00', 1),
('Android Uygulama Geliştirme', 'Android platformu için mobil uygulama geliştirme teknikleri ve best practices', 'Mühendis Ayşe Demir', 'Hafta sonu', '2024-03-01 09:00:00', '2024-06-30 17:00:00', 1),
('Veritabanı Yönetimi', 'MySQL ve PostgreSQL veritabanı yönetimi, optimizasyon ve güvenlik', 'Prof. Dr. Ali Kaya', 'Online', '2024-02-15 20:00:00', '2024-05-15 22:00:00', 1),
('Web Tasarım', 'HTML, CSS, JavaScript ile modern web tasarımı', 'Tasarımcı Fatma Şen', 'Hafta içi Gündüz', '2024-01-15 14:00:00', '2024-03-15 17:00:00', 1),
('Python Programlama', 'Python ile veri analizi ve makine öğrenmesi', 'Dr. Osman Çelik', 'Hafta sonu', '2024-04-01 10:00:00', '2024-07-01 16:00:00', 1);

-- Öğrenci-Program ilişkileri
INSERT INTO `ogrenci_program` (`ogrenci_id`, `program_id`) VALUES
-- Test öğrencisi (ID: 1) - 3 ders
(1, 1), -- Java Programlama
(1, 2), -- Android Geliştirme
(1, 3), -- Veritabanı Yönetimi

-- Ahmet Yılmaz (ID: 2) - 2 ders
(2, 1), -- Java Programlama
(2, 4), -- Web Tasarım

-- Ayşe Demir (ID: 3) - 2 ders
(3, 2), -- Android Geliştirme
(3, 5), -- Python Programlama

-- Mehmet Kaya (ID: 4) - 1 ders
(4, 3); -- Veritabanı Yönetimi

-- İndeksler oluştur (performans için)
CREATE INDEX idx_ogrenciler_email ON ogrenciler(email);
CREATE INDEX idx_ogrenciler_aktif ON ogrenciler(aktif);
CREATE INDEX idx_programlar_aktif ON programlar(aktif);
CREATE INDEX idx_ogrenci_program_ogrenci ON ogrenci_program(ogrenci_id);
CREATE INDEX idx_ogrenci_program_program ON ogrenci_program(program_id);

-- Veritabanı bilgilerini göster
SELECT 'Veritabanı başarıyla oluşturuldu!' as Mesaj;
SELECT COUNT(*) as 'Toplam Öğrenci' FROM ogrenciler;
SELECT COUNT(*) as 'Toplam Program' FROM programlar;
SELECT COUNT(*) as 'Toplam Kayıt' FROM ogrenci_program;

-- Test sorguları
SELECT 'Test Sorguları:' as Baslik;

-- Öğrenci giriş testi
SELECT 'Login Test:' as Test, 
       CASE WHEN COUNT(*) > 0 THEN 'BAŞARILI' ELSE 'BAŞARISIZ' END as Sonuc
FROM ogrenciler 
WHERE email = 'test@rehberhoca.com' AND sifre = '123456' AND aktif = 1;

-- Öğrenci dersleri testi
SELECT 'Courses Test:' as Test,
       COUNT(*) as 'Ders Sayısı'
FROM programlar p
INNER JOIN ogrenci_program op ON p.id = op.program_id
WHERE op.ogrenci_id = 1 AND p.aktif = 1;
