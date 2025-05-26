<?php
/**
 * Student Model
 * Öğrenci veritabanı işlemleri için model sınıfı
 */

class Student {
    private $conn;
    private $table_name = "ogrenciler";

    // Öğrenci özellikleri (MySQL tablo yapısına göre)
    public $id;
    public $ad_soyad;
    public $email;
    public $sifre;
    public $telefon;
    public $kayit_tarihi;
    public $aktif;

    public function __construct($db) {
        $this->conn = $db;
    }

    /**
     * Email ve şifre ile öğrenci girişi
     */
    public function login($email, $sifre) {
        try {
            // SQL sorgusu - email ve şifre kontrolü
            $query = "SELECT id, ad_soyad, email, telefon, kayit_tarihi, aktif
                     FROM " . $this->table_name . "
                     WHERE email = ? AND sifre = ?";

            $stmt = $this->conn->prepare($query);
            $stmt->execute([$email, $sifre]);

            if($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);

                // Öğrenci bilgilerini set et
                $this->id = $row['id'];
                $this->ad_soyad = $row['ad_soyad'];
                $this->email = $row['email'];
                $this->telefon = $row['telefon'];
                $this->kayit_tarihi = $row['kayit_tarihi'];
                $this->aktif = $row['aktif'];

                return true;
            }

            return false;

        } catch(PDOException $exception) {
            error_log("Login hatası: " . $exception->getMessage());
            return false;
        }
    }

    /**
     * ID ile öğrenci bilgilerini getir
     */
    public function getById($id) {
        try {
            $query = "SELECT id, adSoyad, email, telefon, kayitTarihi, aktif
                     FROM " . $this->table_name . "
                     WHERE id = :id AND aktif = 1";

            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(":id", $id);
            $stmt->execute();

            if($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);

                $this->id = $row['id'];
                $this->adSoyad = $row['adSoyad'];
                $this->email = $row['email'];
                $this->telefon = $row['telefon'];
                $this->kayitTarihi = $row['kayitTarihi'];
                $this->aktif = $row['aktif'];

                return true;
            }

            return false;

        } catch(PDOException $exception) {
            error_log("Öğrenci getirme hatası: " . $exception->getMessage());
            return false;
        }
    }

    /**
     * Email kontrolü - email var mı?
     */
    public function emailExists($email) {
        try {
            $query = "SELECT id FROM " . $this->table_name . " WHERE email = :email";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(":email", $email);
            $stmt->execute();

            return $stmt->rowCount() > 0;

        } catch(PDOException $exception) {
            error_log("Email kontrol hatası: " . $exception->getMessage());
            return false;
        }
    }

    /**
     * Öğrenci bilgilerini array olarak döndür
     */
    public function toArray() {
        return [
            "id" => $this->id,
            "ad_soyad" => $this->ad_soyad,
            "email" => $this->email,
            "telefon" => $this->telefon,
            "kayit_tarihi" => $this->kayit_tarihi,
            "aktif" => (bool)$this->aktif
        ];
    }
}
?>
