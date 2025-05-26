<?php
// Hata raporlamayı kapat
error_reporting(0);
ini_set('display_errors', 0);

// CORS headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With");

// OPTIONS request için
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    exit(0);
}

// Sadece POST isteklerini kabul et
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode([
        "success" => false,
        "message" => "Sadece POST istekleri kabul edilir"
    ]);
    exit();
}

// Gerekli dosyaları dahil et
include_once '../config/database.php';
include_once '../models/Student.php';
include_once '../models/StudentProgram.php';

// Veritabanı bağlantısını al
$database = new Database();
$db = $database->getConnection();

// Model'leri oluştur
$student = new Student($db);
$studentProgram = new StudentProgram($db);

// POST verilerini al
$data = json_decode(file_get_contents("php://input"));

// Veri kontrolü
if (empty($data->email) || empty($data->sifre)) {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "E-posta ve şifre gerekli"
    ]);
    exit();
}

// Email formatını kontrol et
if (!filter_var($data->email, FILTER_VALIDATE_EMAIL)) {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "Geçerli bir e-posta adresi girin"
    ]);
    exit();
}

try {
    // Giriş işlemini dene
    if ($student->login($data->email, $data->sifre)) {
        // Öğrenci programlarını getir
        $programs = $studentProgram->getByStudentId($student->id);

        // Başarılı giriş
        http_response_code(200);
        echo json_encode([
            "success" => true,
            "message" => "Giriş başarılı",
            "student" => [
                "id" => $student->id,
                "ad_soyad" => $student->ad_soyad,
                "email" => $student->email,
                "telefon" => $student->telefon,
                "kayit_tarihi" => $student->kayit_tarihi,
                "aktif" => (bool)$student->aktif
            ],
            "programs" => $programs['data'],
            "program_count" => $programs['count']
        ]);
    } else {
        // Başarısız giriş
        http_response_code(401);
        echo json_encode([
            "success" => false,
            "message" => "Geçersiz e-posta veya şifre"
        ]);
    }
} catch (Exception $e) {
    // Hata durumu
    error_log("Login API hatası: " . $e->getMessage());
    http_response_code(500);
    echo json_encode([
        "success" => false,
        "message" => "Sunucu hatası oluştu: " . $e->getMessage()
    ]);
}

// Veritabanı bağlantısını kapat
$database->closeConnection();
?>
