<?php
error_reporting(0);
ini_set('display_errors', 0);

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    exit(0);
}

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    echo json_encode(["success" => false, "message" => "Sadece POST"]);
    exit;
}

$input = file_get_contents("php://input");
$data = json_decode($input, true);

if (!$data || !isset($data['email']) || !isset($data['sifre'])) {
    echo json_encode(["success" => false, "message" => "Email ve şifre gerekli"]);
    exit;
}

try {
    $host = "localhost";
    $dbname = "rehber_hoca_db";
    $username = "root";
    $password = "";
    
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8mb4", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $stmt = $pdo->prepare("SELECT id, ad_soyad, email, telefon FROM ogrenciler WHERE email = ? AND sifre = ?");
    $stmt->execute([$data['email'], $data['sifre']]);
    $student = $stmt->fetch(PDO::FETCH_ASSOC);
    
    if ($student) {
        // Programları getir
        $stmt2 = $pdo->prepare("SELECT * FROM ogrenci_program_atamalari WHERE ogrenci_id = ?");
        $stmt2->execute([$student['id']]);
        $programs = $stmt2->fetchAll(PDO::FETCH_ASSOC);
        
        echo json_encode([
            "success" => true,
            "message" => "Giriş başarılı",
            "student" => $student,
            "programs" => $programs,
            "program_count" => count($programs)
        ]);
    } else {
        echo json_encode([
            "success" => false,
            "message" => "Email veya şifre hatalı"
        ]);
    }
    
} catch(Exception $e) {
    echo json_encode([
        "success" => false,
        "message" => "Hata: " . $e->getMessage()
    ]);
}
?>
