<?php
/**
 * API Test Sayfası
 * Bu dosya API'nin doğru çalışıp çalışmadığını test etmek için kullanılır
 */
?>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rehber Hoca Mobile API Test</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .test-section { margin: 20px 0; padding: 15px; border: 1px solid #ddd; border-radius: 5px; }
        .success { background-color: #d4edda; border-color: #c3e6cb; color: #155724; }
        .error { background-color: #f8d7da; border-color: #f5c6cb; color: #721c24; }
        .info { background-color: #d1ecf1; border-color: #bee5eb; color: #0c5460; }
        button { background-color: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin: 5px; }
        button:hover { background-color: #0056b3; }
        pre { background-color: #f8f9fa; padding: 10px; border-radius: 4px; overflow-x: auto; }
        .endpoint { font-family: monospace; background-color: #e9ecef; padding: 2px 6px; border-radius: 3px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚀 Rehber Hoca Mobile API Test</h1>
        
        <div class="test-section info">
            <h3>📋 Sistem Bilgileri</h3>
            <p><strong>PHP Versiyonu:</strong> <?php echo phpversion(); ?></p>
            <p><strong>Server:</strong> <?php echo $_SERVER['SERVER_SOFTWARE']; ?></p>
            <p><strong>Tarih:</strong> <?php echo date('Y-m-d H:i:s'); ?></p>
        </div>

        <div class="test-section">
            <h3>🔧 Gereksinimler Kontrolü</h3>
            <?php
            $requirements = [
                'PDO' => extension_loaded('pdo'),
                'PDO MySQL' => extension_loaded('pdo_mysql'),
                'JSON' => extension_loaded('json'),
                'mod_rewrite' => function_exists('apache_get_modules') ? in_array('mod_rewrite', apache_get_modules()) : 'Bilinmiyor'
            ];
            
            foreach ($requirements as $req => $status) {
                $class = ($status === true) ? 'success' : (($status === false) ? 'error' : 'info');
                $text = ($status === true) ? '✅ Aktif' : (($status === false) ? '❌ Pasif' : $status);
                echo "<div class='$class'><strong>$req:</strong> $text</div>";
            }
            ?>
        </div>

        <div class="test-section">
            <h3>🗄️ Veritabanı Bağlantı Testi</h3>
            <?php
            try {
                include_once 'config/database.php';
                $database = new Database();
                $db = $database->getConnection();
                
                if ($db) {
                    echo "<div class='success'>✅ Veritabanı bağlantısı başarılı!</div>";
                    
                    // Tabloları kontrol et
                    $tables = ['ogrenciler', 'programlar', 'ogrenci_program'];
                    foreach ($tables as $table) {
                        $stmt = $db->query("SHOW TABLES LIKE '$table'");
                        if ($stmt->rowCount() > 0) {
                            echo "<div class='success'>✅ Tablo '$table' mevcut</div>";
                        } else {
                            echo "<div class='error'>❌ Tablo '$table' bulunamadı</div>";
                        }
                    }
                    
                    $database->closeConnection();
                } else {
                    echo "<div class='error'>❌ Veritabanı bağlantısı başarısız!</div>";
                }
            } catch (Exception $e) {
                echo "<div class='error'>❌ Hata: " . $e->getMessage() . "</div>";
            }
            ?>
        </div>

        <div class="test-section">
            <h3>🔗 API Endpoint Testleri</h3>
            
            <h4>1. Login API Testi</h4>
            <p><span class="endpoint">POST /api/mobile/login</span></p>
            <button onclick="testLogin()">Login Test Et</button>
            <div id="loginResult"></div>
            
            <h4>2. Student Courses API Testi</h4>
            <p><span class="endpoint">GET /api/mobile/student/1/courses</span></p>
            <button onclick="testCourses()">Courses Test Et</button>
            <div id="coursesResult"></div>
            
            <h4>3. Student Profile API Testi</h4>
            <p><span class="endpoint">GET /api/mobile/student/profile/1</span></p>
            <button onclick="testProfile()">Profile Test Et</button>
            <div id="profileResult"></div>
        </div>

        <div class="test-section info">
            <h3>📱 Android Uygulama Konfigürasyonu</h3>
            <p>Android uygulamanızda aşağıdaki URL'yi kullanın:</p>
            <pre>
// Android Emulator için
private static final String BASE_URL = "http://10.0.2.2/rehberhoca/";

// Fiziksel cihaz için (IP adresinizi yazın)
private static final String BASE_URL = "http://<?php echo $_SERVER['SERVER_ADDR']; ?>/rehberhoca/";
            </pre>
        </div>
    </div>

    <script>
        function testLogin() {
            const resultDiv = document.getElementById('loginResult');
            resultDiv.innerHTML = '<div class="info">Test ediliyor...</div>';
            
            fetch('api/mobile/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: 'test@rehberhoca.com',
                    sifre: '123456'
                })
            })
            .then(response => response.json())
            .then(data => {
                const className = data.success ? 'success' : 'error';
                resultDiv.innerHTML = `<div class="${className}"><pre>${JSON.stringify(data, null, 2)}</pre></div>`;
            })
            .catch(error => {
                resultDiv.innerHTML = `<div class="error">❌ Hata: ${error.message}</div>`;
            });
        }
        
        function testCourses() {
            const resultDiv = document.getElementById('coursesResult');
            resultDiv.innerHTML = '<div class="info">Test ediliyor...</div>';
            
            fetch('api/mobile/student/1/courses')
            .then(response => response.json())
            .then(data => {
                const className = Array.isArray(data) ? 'success' : 'error';
                resultDiv.innerHTML = `<div class="${className}"><pre>${JSON.stringify(data, null, 2)}</pre></div>`;
            })
            .catch(error => {
                resultDiv.innerHTML = `<div class="error">❌ Hata: ${error.message}</div>`;
            });
        }
        
        function testProfile() {
            const resultDiv = document.getElementById('profileResult');
            resultDiv.innerHTML = '<div class="info">Test ediliyor...</div>';
            
            fetch('api/mobile/student/profile/1')
            .then(response => response.json())
            .then(data => {
                const className = data.id ? 'success' : 'error';
                resultDiv.innerHTML = `<div class="${className}"><pre>${JSON.stringify(data, null, 2)}</pre></div>`;
            })
            .catch(error => {
                resultDiv.innerHTML = `<div class="error">❌ Hata: ${error.message}</div>`;
            });
        }
    </script>
</body>
</html>
