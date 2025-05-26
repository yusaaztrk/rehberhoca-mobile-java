<?php
// Basit test dosyası
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Veritabanı bağlantısını test et
include_once 'config/database.php';

try {
    $database = new Database();
    $db = $database->getConnection();

    // Öğrenci sayısını kontrol et
    $query = "SELECT COUNT(*) as total FROM ogrenciler";
    $stmt = $db->prepare($query);
    $stmt->execute();
    $result = $stmt->fetch();

    echo json_encode([
        "success" => true,
        "message" => "PHP Backend ve MySQL çalışıyor!",
        "database" => "rehber_hoca_db",
        "student_count" => $result['total'],
        "timestamp" => date('Y-m-d H:i:s')
    ]);

} catch(Exception $e) {
    echo json_encode([
        "success" => false,
        "message" => "Hata: " . $e->getMessage(),
        "timestamp" => date('Y-m-d H:i:s')
    ]);
}
?>
