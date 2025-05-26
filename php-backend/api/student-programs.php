<?php
/**
 * Öğrenci Programları API Endpoint
 * GET /api/student-programs.php?student_id=X
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
include_once '../models/StudentProgram.php';

// Veritabanı bağlantısını al
$database = new Database();
$db = $database->getConnection();

// StudentProgram model oluştur
$studentProgram = new StudentProgram($db);

// student_id parametresini kontrol et
if (!isset($_GET['student_id']) || empty($_GET['student_id'])) {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "student_id parametresi gerekli"
    ]);
    exit();
}

$student_id = intval($_GET['student_id']);

// Öğrenci ID'sinin geçerli olup olmadığını kontrol et
if ($student_id <= 0) {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "Geçerli bir student_id girin"
    ]);
    exit();
}

try {
    // Aktif programları mı yoksa tüm programları mı getireceğiz?
    $active_only = isset($_GET['active_only']) && $_GET['active_only'] === 'true';
    
    if ($active_only) {
        // Sadece aktif programları getir
        $result = $studentProgram->getActiveByStudentId($student_id);
    } else {
        // Tüm programları getir
        $result = $studentProgram->getByStudentId($student_id);
    }
    
    if ($result['success']) {
        http_response_code(200);
        echo json_encode([
            "success" => true,
            "message" => "Programlar başarıyla getirildi",
            "data" => $result['data'],
            "count" => $result['count'],
            "student_id" => $student_id,
            "active_only" => $active_only
        ]);
    } else {
        http_response_code(404);
        echo json_encode([
            "success" => false,
            "message" => $result['message'],
            "data" => [],
            "count" => 0
        ]);
    }
    
} catch (Exception $e) {
    // Hata durumu
    error_log("Student Programs API hatası: " . $e->getMessage());
    http_response_code(500);
    echo json_encode([
        "success" => false,
        "message" => "Sunucu hatası oluştu: " . $e->getMessage(),
        "data" => [],
        "count" => 0
    ]);
}

// Veritabanı bağlantısını kapat
$database->closeConnection();
