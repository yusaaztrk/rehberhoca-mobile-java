<?php
/**
 * SQL Server Bağlantı Test Dosyası
 * Bu dosya SQL Server bağlantısını test eder
 */

// CORS headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Gerekli dosyaları dahil et
include_once 'config/database.php';

try {
    // Veritabanı bağlantısını test et
    $database = new Database();
    $db = $database->getConnection();
    
    if ($db) {
        // Bağlantı başarılı - öğrenciler tablosunu kontrol et
        $query = "SELECT COUNT(*) as total FROM ogrenciler";
        $stmt = $db->prepare($query);
        $stmt->execute();
        $result = $stmt->fetch();
        
        echo json_encode([
            "success" => true,
            "message" => "SQL Server bağlantısı başarılı",
            "database_info" => [
                "connection_status" => "Connected",
                "total_students" => $result['total'],
                "server_time" => date('Y-m-d H:i:s')
            ]
        ]);
    } else {
        echo json_encode([
            "success" => false,
            "message" => "Veritabanı bağlantısı başarısız"
        ]);
    }
    
} catch (Exception $e) {
    echo json_encode([
        "success" => false,
        "message" => "Bağlantı hatası: " . $e->getMessage(),
        "error_details" => [
            "error_code" => $e->getCode(),
            "error_file" => $e->getFile(),
            "error_line" => $e->getLine()
        ]
    ]);
}
?>
