<?php
/**
 * StudentProgram Model
 * Öğrenci program atamaları için model sınıfı
 */

class StudentProgram {
    private $conn;
    private $table_name = "ogrenci_program_atamalari";
    
    // Öğrenci program özellikleri
    public $id;
    public $ogrenci_id;
    public $program_id;
    public $atama_tarihi;
    public $durum;
    public $notlar;
    public $olusturan;
    public $guncelleme_tarihi;

    public function __construct($db) {
        $this->conn = $db;
    }

    /**
     * Öğrenci ID'sine göre programları getir
     */
    public function getByStudentId($ogrenci_id) {
        try {
            $query = "SELECT id, ogrenci_id, program_id, atama_tarihi, durum, notlar, olusturan, guncelleme_tarihi
                     FROM " . $this->table_name . "
                     WHERE ogrenci_id = ?
                     ORDER BY atama_tarihi DESC";
            
            $stmt = $this->conn->prepare($query);
            $stmt->execute([$ogrenci_id]);
            
            $programs = [];
            while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                $programs[] = [
                    "id" => $row['id'],
                    "ogrenci_id" => $row['ogrenci_id'],
                    "program_id" => $row['program_id'],
                    "atama_tarihi" => $row['atama_tarihi'],
                    "durum" => $row['durum'],
                    "notlar" => $row['notlar'],
                    "olusturan" => $row['olusturan'],
                    "guncelleme_tarihi" => $row['guncelleme_tarihi']
                ];
            }
            
            return [
                "success" => true,
                "data" => $programs,
                "count" => count($programs)
            ];

        } catch(PDOException $exception) {
            error_log("Öğrenci programları getirme hatası: " . $exception->getMessage());
            return [
                "success" => false,
                "message" => "Programlar getirilirken hata oluştu: " . $exception->getMessage(),
                "data" => []
            ];
        }
    }

    /**
     * Belirli bir program atamasını getir
     */
    public function getById($id) {
        try {
            $query = "SELECT id, ogrenci_id, program_id, atama_tarihi, durum, notlar, olusturan, guncelleme_tarihi
                     FROM " . $this->table_name . "
                     WHERE id = ?";
            
            $stmt = $this->conn->prepare($query);
            $stmt->execute([$id]);
            
            if($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                
                return [
                    "success" => true,
                    "data" => [
                        "id" => $row['id'],
                        "ogrenci_id" => $row['ogrenci_id'],
                        "program_id" => $row['program_id'],
                        "atama_tarihi" => $row['atama_tarihi'],
                        "durum" => $row['durum'],
                        "notlar" => $row['notlar'],
                        "olusturan" => $row['olusturan'],
                        "guncelleme_tarihi" => $row['guncelleme_tarihi']
                    ]
                ];
            } else {
                return [
                    "success" => false,
                    "message" => "Program ataması bulunamadı",
                    "data" => null
                ];
            }

        } catch(PDOException $exception) {
            error_log("Program ataması getirme hatası: " . $exception->getMessage());
            return [
                "success" => false,
                "message" => "Program ataması getirilirken hata oluştu: " . $exception->getMessage(),
                "data" => null
            ];
        }
    }

    /**
     * Aktif program atamalarını getir
     */
    public function getActiveByStudentId($ogrenci_id) {
        try {
            $query = "SELECT id, ogrenci_id, program_id, atama_tarihi, durum, notlar, olusturan, guncelleme_tarihi
                     FROM " . $this->table_name . "
                     WHERE ogrenci_id = ? AND durum = 'Aktif'
                     ORDER BY atama_tarihi DESC";
            
            $stmt = $this->conn->prepare($query);
            $stmt->execute([$ogrenci_id]);
            
            $programs = [];
            while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                $programs[] = [
                    "id" => $row['id'],
                    "ogrenci_id" => $row['ogrenci_id'],
                    "program_id" => $row['program_id'],
                    "atama_tarihi" => $row['atama_tarihi'],
                    "durum" => $row['durum'],
                    "notlar" => $row['notlar'],
                    "olusturan" => $row['olusturan'],
                    "guncelleme_tarihi" => $row['guncelleme_tarihi']
                ];
            }
            
            return [
                "success" => true,
                "data" => $programs,
                "count" => count($programs)
            ];

        } catch(PDOException $exception) {
            error_log("Aktif programlar getirme hatası: " . $exception->getMessage());
            return [
                "success" => false,
                "message" => "Aktif programlar getirilirken hata oluştu: " . $exception->getMessage(),
                "data" => []
            ];
        }
    }
}
