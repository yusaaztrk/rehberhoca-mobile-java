<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

echo json_encode([
    "php_version" => phpversion(),
    "extensions" => [
        "pdo" => extension_loaded('pdo'),
        "pdo_mysql" => extension_loaded('pdo_mysql'),
        "pdo_sqlsrv" => extension_loaded('pdo_sqlsrv')
    ],
    "server_info" => [
        "document_root" => $_SERVER['DOCUMENT_ROOT'],
        "script_name" => $_SERVER['SCRIPT_NAME'],
        "server_name" => $_SERVER['SERVER_NAME'],
        "server_port" => $_SERVER['SERVER_PORT']
    ],
    "current_time" => date('Y-m-d H:i:s'),
    "file_exists" => [
        "config/database.php" => file_exists(__DIR__ . '/config/database.php'),
        "models/Student.php" => file_exists(__DIR__ . '/models/Student.php'),
        "api/login.php" => file_exists(__DIR__ . '/api/login.php')
    ]
]);
?>
