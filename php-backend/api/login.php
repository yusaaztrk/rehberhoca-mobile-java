<?php
/**
 * Öğrenci Giriş API Endpoint
 * POST /api/login.php
 */

// CORS headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

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

// Veritabanı bağlantısını al
$database = new Database();
$db = $database->getConnection();

// Student model oluştur
$student = new Student($db);

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
        // Başarılı giriş
        http_response_code(200);
        echo json_encode([
            "success" => true,
            "message" => "Giriş başarılı",
            "student" => $student->toArray()
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
        "message" => "Sunucu hatası oluştu"
    ]);
}

// Veritabanı bağlantısını kapat
$database->closeConnection();
?>
