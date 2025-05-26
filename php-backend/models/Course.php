<?php
/**
 * Course Model
 * Ders/Program veritabanı işlemleri için model sınıfı
 */

class Course {
    private $conn;
    private $table_name = "programlar"; // Ana program tablosu
    private $junction_table = "ogrenci_program"; // Öğrenci-program ilişki tablosu
    
    // Ders özellikleri
    public $id;
    public $ad;
    public $aciklama;
    public $egitmen;
    public $program;
    public $baslangicTarihi;
    public $bitisTarihi;
    public $aktif;
    
    public function __construct($db) {
        $this->conn = $db;
    }
    
    /**
     * Öğrencinin atanmış derslerini getir
     */
    public function getStudentCourses($studentId) {
        try {
            $query = "SELECT p.id, p.ad, p.aciklama, p.egitmen, p.kategori as program, 
                            p.baslangicTarihi, p.bitisTarihi, p.aktif
                     FROM " . $this->table_name . " p
                     INNER JOIN " . $this->junction_table . " op ON p.id = op.program_id
                     WHERE op.ogrenci_id = :student_id AND p.aktif = 1
                     ORDER BY p.baslangicTarihi DESC";
            
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(":student_id", $studentId);
            $stmt->execute();
            
            $courses = [];
            
            while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                $course = [
                    "id" => (int)$row['id'],
                    "ad" => $row['ad'],
                    "aciklama" => $row['aciklama'],
                    "egitmen" => $row['egitmen'],
                    "program" => $row['program'],
                    "baslangicTarihi" => $row['baslangicTarihi'],
                    "bitisTarihi" => $row['bitisTarihi'],
                    "aktif" => (bool)$row['aktif']
                ];
                
                array_push($courses, $course);
            }
            
            return $courses;
            
        } catch(PDOException $exception) {
            error_log("Ders getirme hatası: " . $exception->getMessage());
            return [];
        }
    }
    
    /**
     * ID ile ders bilgilerini getir
     */
    public function getById($id) {
        try {
            $query = "SELECT id, ad, aciklama, egitmen, kategori as program, 
                            baslangicTarihi, bitisTarihi, aktif
                     FROM " . $this->table_name . " 
                     WHERE id = :id AND aktif = 1";
            
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(":id", $id);
            $stmt->execute();
            
            if($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                
                $this->id = $row['id'];
                $this->ad = $row['ad'];
                $this->aciklama = $row['aciklama'];
                $this->egitmen = $row['egitmen'];
                $this->program = $row['program'];
                $this->baslangicTarihi = $row['baslangicTarihi'];
                $this->bitisTarihi = $row['bitisTarihi'];
                $this->aktif = $row['aktif'];
                
                return true;
            }
            
            return false;
            
        } catch(PDOException $exception) {
            error_log("Ders getirme hatası: " . $exception->getMessage());
            return false;
        }
    }
    
    /**
     * Öğrencinin belirli bir derse kayıtlı olup olmadığını kontrol et
     */
    public function isStudentEnrolled($studentId, $courseId) {
        try {
            $query = "SELECT id FROM " . $this->junction_table . " 
                     WHERE ogrenci_id = :student_id AND program_id = :course_id";
            
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(":student_id", $studentId);
            $stmt->bindParam(":course_id", $courseId);
            $stmt->execute();
            
            return $stmt->rowCount() > 0;
            
        } catch(PDOException $exception) {
            error_log("Kayıt kontrol hatası: " . $exception->getMessage());
            return false;
        }
    }
    
    /**
     * Ders bilgilerini array olarak döndür
     */
    public function toArray() {
        return [
            "id" => $this->id,
            "ad" => $this->ad,
            "aciklama" => $this->aciklama,
            "egitmen" => $this->egitmen,
            "program" => $this->program,
            "baslangicTarihi" => $this->baslangicTarihi,
            "bitisTarihi" => $this->bitisTarihi,
            "aktif" => (bool)$this->aktif
        ];
    }
}
?>
