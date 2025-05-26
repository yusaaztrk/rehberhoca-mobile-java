<?php
/**
 * Veritabanı Bağlantı Konfigürasyonu
 * Rehber Hoca Mobile API için SQL Server bağlantısı
 */

class Database {
    // SQL Server veritabanı bilgileri - kendi bilgilerinizle güncelleyin
    private $host = "localhost"; // SQL Server host adresi
    private $db_name = "RehberHoca"; // Veritabanı adınız
    private $username = "sa"; // SQL Server kullanıcı adı
    private $password = ""; // SQL Server şifresi
    private $port = "1433"; // SQL Server port (varsayılan 1433)

    public $conn;

    /**
     * SQL Server veritabanı bağlantısını al
     */
    public function getConnection() {
        $this->conn = null;

        try {
            // SQL Server için DSN
            $dsn = "sqlsrv:Server=" . $this->host . "," . $this->port . ";Database=" . $this->db_name;
            $this->conn = new PDO($dsn, $this->username, $this->password);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->conn->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
        } catch(PDOException $exception) {
            error_log("SQL Server bağlantı hatası: " . $exception->getMessage());
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
