<?php
/**
 * Veritabanı Bağlantı Konfigürasyonu
 * Rehber Hoca Mobile API için MySQL bağlantısı
 */

class Database {
    // MySQL veritabanı bilgileri
    private $host = "localhost";
    private $db_name = "rehber_hoca_db"; // Sizin veritabanı adınız
    private $username = "root";
    private $password = "";
    private $charset = "utf8mb4";

    public $conn;

    /**
     * MySQL veritabanı bağlantısını al
     */
    public function getConnection() {
        $this->conn = null;

        try {
            $dsn = "mysql:host=" . $this->host . ";dbname=" . $this->db_name . ";charset=" . $this->charset;
            $this->conn = new PDO($dsn, $this->username, $this->password);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->conn->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
        } catch(PDOException $exception) {
            error_log("MySQL bağlantı hatası: " . $exception->getMessage());
            echo json_encode([
                "success" => false,
                "message" => "Veritabanı bağlantı hatası: " . $exception->getMessage()
            ]);
            exit();
        }

        return $this->conn;
    }

    /**
     * Bağlantıyı kapat
     */
    public function closeConnection() {
        $this->conn = null;
    }
}
?>
