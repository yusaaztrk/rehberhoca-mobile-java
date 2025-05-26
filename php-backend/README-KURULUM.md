# Rehber Hoca Mobile API - PHP Backend Kurulum Rehberi

Bu rehber, Apache server üzerinde PHP ile MySQL veritabanına bağlanan API'nin kurulumunu açıklar.

## 📋 Gereksinimler

- **Apache Web Server** (XAMPP, WAMP, LAMP veya canlı sunucu)
- **PHP 7.4+** (PDO desteği ile)
- **MySQL 5.7+** veya **MariaDB**
- **mod_rewrite** modülü aktif

## 🚀 Kurulum Adımları

### 1. Dosyaları Sunucuya Yükleyin

```
htdocs/rehberhoca/  (XAMPP için)
├── config/
│   └── database.php
├── models/
│   ├── Student.php
│   └── Course.php
├── api/
│   ├── login.php
│   ├── student_courses.php
│   └── student_profile.php
├── .htaccess
└── test.php
```

### 2. Veritabanı Konfigürasyonu

`config/database.php` dosyasını düzenleyin:

```php
private $host = "localhost";        // MySQL sunucu adresi
private $db_name = "rehberhoca_db"; // Veritabanı adınız
private $username = "root";         // MySQL kullanıcı adı
private $password = "";             // MySQL şifresi
```

### 3. Veritabanı Tabloları

Aşağıdaki tablo yapısının mevcut olduğundan emin olun:

#### `ogrenciler` Tablosu:
```sql
CREATE TABLE `ogrenciler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adSoyad` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `sifre` varchar(255) NOT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `kayitTarihi` datetime DEFAULT CURRENT_TIMESTAMP,
  `aktif` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
);
```

#### `programlar` Tablosu:
```sql
CREATE TABLE `programlar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(255) NOT NULL,
  `aciklama` text,
  `egitmen` varchar(255) DEFAULT NULL,
  `kategori` varchar(100) DEFAULT NULL,
  `baslangicTarihi` datetime DEFAULT NULL,
  `bitisTarihi` datetime DEFAULT NULL,
  `aktif` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
);
```

#### `ogrenci_program` İlişki Tablosu:
```sql
CREATE TABLE `ogrenci_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ogrenci_id` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  `kayitTarihi` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`ogrenci_id`) REFERENCES `ogrenciler`(`id`),
  FOREIGN KEY (`program_id`) REFERENCES `programlar`(`id`)
);
```

### 4. Test Verisi Ekleyin

```sql
-- Test öğrencisi
INSERT INTO `ogrenciler` (`adSoyad`, `email`, `sifre`, `telefon`, `aktif`) 
VALUES ('Test Öğrenci', 'test@rehberhoca.com', '123456', '05551234567', 1);

-- Test programı
INSERT INTO `programlar` (`ad`, `aciklama`, `egitmen`, `kategori`, `baslangicTarihi`, `bitisTarihi`, `aktif`) 
VALUES ('Java Programlama', 'Java temel eğitimi', 'Dr. Mehmet Özkan', 'Hafta içi Akşam', '2024-02-01 18:00:00', '2024-04-30 21:00:00', 1);

-- Öğrenci-program ilişkisi
INSERT INTO `ogrenci_program` (`ogrenci_id`, `program_id`) 
VALUES (1, 1);
```

### 5. Apache Konfigürasyonu

`.htaccess` dosyasının doğru çalıştığından emin olun. `mod_rewrite` modülü aktif olmalı.

XAMPP için `httpd.conf` dosyasında:
```apache
LoadModule rewrite_module modules/mod_rewrite.so
```

### 6. API Test Etme

Tarayıcıda test edin:
```
http://localhost/rehberhoca/test.php
```

## 🔧 API Endpoint'leri

### 1. Öğrenci Girişi
```
POST http://localhost/rehberhoca/api/mobile/login
Content-Type: application/json

{
    "email": "test@rehberhoca.com",
    "sifre": "123456"
}
```

### 2. Öğrenci Dersleri
```
GET http://localhost/rehberhoca/api/mobile/student/1/courses
```

### 3. Öğrenci Profili
```
GET http://localhost/rehberhoca/api/mobile/student/profile/1
```

## 📱 Android Uygulaması Konfigürasyonu

`ApiClient.java` dosyasında URL'yi güncelleyin:

```java
// Android Emulator için
private static final String BASE_URL = "http://10.0.2.2/rehberhoca/";

// Fiziksel cihaz için (IP adresinizi yazın)
private static final String BASE_URL = "http://192.168.1.100/rehberhoca/";

// Localhost test için
private static final String BASE_URL = "http://localhost/rehberhoca/";
```

## 🐛 Sorun Giderme

### 1. "Kaynak bulunamadı" Hatası
- Apache server çalışıyor mu kontrol edin
- URL doğru mu kontrol edin
- `.htaccess` dosyası var mı kontrol edin

### 2. Veritabanı Bağlantı Hatası
- MySQL servisi çalışıyor mu kontrol edin
- `database.php` dosyasındaki bilgiler doğru mu kontrol edin
- Veritabanı ve tablolar oluşturulmuş mu kontrol edin

### 3. CORS Hatası
- `.htaccess` dosyasında CORS headers var mı kontrol edin
- Apache'de `mod_headers` modülü aktif mi kontrol edin

### 4. 404 Hatası
- `mod_rewrite` modülü aktif mi kontrol edin
- `.htaccess` dosyası doğru konumda mı kontrol edin

## 📝 Log Dosyaları

Hata loglarını kontrol edin:
- Apache error log: `/xampp/apache/logs/error.log`
- PHP error log: `/xampp/php/logs/php_error_log`

## 🔒 Güvenlik Notları

- Canlı sunucuda şifreleri hash'leyin (password_hash/password_verify)
- HTTPS kullanın
- SQL injection koruması için PDO prepared statements kullanılıyor
- Input validation yapılıyor

## 📞 Destek

Sorun yaşarsanız:
1. Apache ve MySQL servislerinin çalıştığından emin olun
2. Error loglarını kontrol edin
3. Test endpoint'lerini tarayıcıda deneyin
4. Network bağlantısını kontrol edin
