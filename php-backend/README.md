# Rehber Hoca PHP Backend

Bu proje, Rehber Hoca mobil uygulaması için SQL Server veritabanı ile çalışan PHP backend API'sidir.

## Gereksinimler

### PHP Gereksinimleri
- PHP 7.4 veya üzeri
- SQL Server için PHP PDO driver'ı (pdo_sqlsrv)
- Microsoft ODBC Driver for SQL Server

### Veritabanı Yapısı

#### `ogrenciler` Tablosu
```sql
CREATE TABLE ogrenciler (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ad_soyad NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) UNIQUE NOT NULL,
    sifre NVARCHAR(255) NOT NULL,
    telefon NVARCHAR(20),
    kayit_tarihi DATETIME DEFAULT GETDATE(),
    aktif BIT DEFAULT 1
);
```

#### `ogrenci_program_atamalari` Tablosu
```sql
CREATE TABLE ogrenci_program_atamalari (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ogrenci_id INT NOT NULL,
    program_id INT NOT NULL,
    atama_tarihi DATETIME DEFAULT GETDATE(),
    durum NVARCHAR(50) DEFAULT 'Aktif',
    notlar NVARCHAR(MAX),
    olusturan NVARCHAR(255),
    guncelleme_tarihi DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ogrenci_id) REFERENCES ogrenciler(id)
);
```

## Kurulum

### 1. SQL Server Driver Kurulumu

#### Windows için:
1. Microsoft ODBC Driver for SQL Server'ı indirin ve kurun
2. PHP SQL Server extension'larını indirin:
   - `php_pdo_sqlsrv.dll`
   - `php_sqlsrv.dll`
3. Bu dosyaları PHP'nin `ext` klasörüne kopyalayın
4. `php.ini` dosyasına şu satırları ekleyin:
   ```ini
   extension=pdo_sqlsrv
   extension=sqlsrv
   ```

### 2. Veritabanı Konfigürasyonu

`config/database.php` dosyasındaki bağlantı bilgilerini güncelleyin:

```php
private $host = "localhost"; // SQL Server host adresi
private $db_name = "RehberHoca"; // Veritabanı adınız
private $username = "sa"; // SQL Server kullanıcı adı
private $password = ""; // SQL Server şifresi
private $port = "1433"; // SQL Server port
```

### 3. Test

1. Web sunucunuzu başlatın (Apache/Nginx)
2. `test-login.html` dosyasını tarayıcıda açın
3. Mevcut bir öğrenci email ve şifresi ile test edin

## API Endpoints

### POST /api/login.php
Öğrenci girişi yapar ve programlarını getirir.

**Request Body:**
```json
{
    "email": "ogrenci@email.com",
    "sifre": "sifre123"
}
```

**Response (Başarılı):**
```json
{
    "success": true,
    "message": "Giriş başarılı",
    "student": {
        "id": 1,
        "ad_soyad": "Ahmet Yılmaz",
        "email": "ahmet.yilmaz@email.com",
        "telefon": "0532 123 4567",
        "kayit_tarihi": "2025-05-19 00:17:07.000000",
        "aktif": true
    },
    "programs": [
        {
            "id": 3,
            "ogrenci_id": 1,
            "program_id": 1,
            "atama_tarihi": "2025-05-26 00:59:40",
            "durum": "Aktif",
            "notlar": null,
            "olusturan": "Sistem",
            "guncelleme_tarihi": "2025-05-26 00:59:40"
        }
    ],
    "program_count": 1
}
```

### GET /api/student-programs.php?student_id=X
Belirli bir öğrencinin programlarını getirir.

**Parameters:**
- `student_id`: Öğrenci ID'si (zorunlu)
- `active_only`: Sadece aktif programları getir (opsiyonel, true/false)

## Hata Ayıklama

1. PHP error log'larını kontrol edin
2. SQL Server bağlantısını test edin
3. Veritabanı tablolarının doğru oluşturulduğundan emin olun
4. PHP SQL Server extension'larının yüklü olduğunu kontrol edin:
   ```php
   <?php
   phpinfo();
   ?>
   ```

## Güvenlik Notları

- Şifreler hash'lenmeli (bcrypt kullanın)
- SQL injection koruması için prepared statements kullanılıyor
- CORS ayarları production için kısıtlanmalı
- HTTPS kullanın
