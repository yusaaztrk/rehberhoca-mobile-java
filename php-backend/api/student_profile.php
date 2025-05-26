<?php
/**
 * Öğrenci Profil API Endpoint
 * GET /api/student_profile.php?student_id=1
 */

// CORS headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Sadece GET isteklerini kabul et
if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    echo json_encode([
        "success" => false,
        "message" => "Sadece GET istekleri kabul edilir"
    ]);
    exit();
}

// Gerekli dosyaları dahil et
include_once '../config/database.php';
include_once '../models/Student.php';

// Veritabanı bağlantısını al
$database = new Database();
$db = $database->getConnection();

// Student model oluştur
$student = new Student($db);

// Student ID parametresini al
$student_id = isset($_GET['student_id']) ? (int)$_GET['student_id'] : 0;

// Student ID kontrolü
if ($student_id <= 0) {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "Geçerli bir öğrenci ID'si gerekli"
    ]);
    exit();
}

try {
    // Öğrenci bilgilerini getir
    if ($student->getById($student_id)) {
        // Başarılı yanıt
        http_response_code(200);
        echo json_encode($student->toArray());
    } else {
        // Öğrenci bulunamadı
        http_response_code(404);
        echo json_encode([
            "success" => false,
            "message" => "Öğrenci bulunamadı"
        ]);
    }
    
} catch (Exception $e) {
    // Hata durumu
    error_log("Student Profile API hatası: " . $e->getMessage());
    http_response_code(500);
    echo json_encode([
        "success" => false,
        "message" => "Sunucu hatası oluştu"
    ]);
}

// Veritabanı bağlantısını kapat
$database->closeConnection();
?>
