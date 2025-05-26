import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import com.sun.net.httpserver.*;

public class SimpleApp {
    private static final int PORT = 8080;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rehber_hoca_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        // CORS için
        server.createContext("/api/auth/test", new TestHandler());
        server.createContext("/api/auth/login", new LoginHandler());
        server.createContext("/api/auth/student", new StudentProgramsHandler());

        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();

        System.out.println("🚀 Simple Spring Backend Started!");
        System.out.println("📍 Server running on: http://localhost:" + PORT);
        System.out.println("🔗 Test URL: http://localhost:" + PORT + "/api/auth/test");
    }

    static class TestHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            setCorsHeaders(exchange);

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
                return;
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Simple Spring Backend çalışıyor!");
            response.put("timestamp", new Date().toString());
            response.put("version", "1.0.0");

            String jsonResponse = new Gson().toJson(response);
            exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }
    }

    static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            setCorsHeaders(exchange);

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
                return;
            }

            if (!"POST".equals(exchange.getRequestMethod())) {
                sendError(exchange, "Sadece POST istekleri kabul edilir");
                return;
            }

            try {
                // Request body'yi oku
                String requestBody = readRequestBody(exchange);
                Map<String, String> loginData = new Gson().fromJson(requestBody, Map.class);

                String email = loginData.get("email");
                String sifre = loginData.get("sifre");

                if (email == null || sifre == null) {
                    sendError(exchange, "Email ve şifre gerekli");
                    return;
                }

                // Database'den kontrol et
                Map<String, Object> result = checkLogin(email, sifre);
                String jsonResponse = new Gson().toJson(result);

                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();

            } catch (Exception e) {
                sendError(exchange, "Hata: " + e.getMessage());
            }
        }
    }

    static class StudentProgramsHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            setCorsHeaders(exchange);

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, 0);
                exchange.close();
                return;
            }

            try {
                String path = exchange.getRequestURI().getPath();
                String[] parts = path.split("/");

                if (parts.length >= 5) {
                    Long studentId = Long.parseLong(parts[4]);
                    Map<String, Object> result = getStudentPrograms(studentId);

                    String jsonResponse = new Gson().toJson(result);
                    exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(jsonResponse.getBytes());
                    os.close();
                } else {
                    sendError(exchange, "Geçersiz URL");
                }
            } catch (Exception e) {
                sendError(exchange, "Hata: " + e.getMessage());
            }
        }
    }

    private static void setCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        exchange.getResponseHeaders().add("Content-Type", "application/json");
    }

    private static String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static void sendError(HttpExchange exchange, String message) throws IOException {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", message);

        String jsonResponse = new Gson().toJson(error);
        exchange.sendResponseHeaders(400, jsonResponse.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes());
        os.close();
    }

    private static Map<String, Object> checkLogin(String email, String sifre) {
        Map<String, Object> response = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "SELECT id, ad_soyad, email, telefon FROM ogrenciler WHERE email = ? AND sifre = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, sifre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Map<String, Object> student = new HashMap<>();
                student.put("id", rs.getLong("id"));
                student.put("ad_soyad", rs.getString("ad_soyad"));
                student.put("email", rs.getString("email"));
                student.put("telefon", rs.getString("telefon"));

                // Programları getir
                List<Map<String, Object>> programs = getPrograms(rs.getLong("id"), conn);

                response.put("success", true);
                response.put("message", "Giriş başarılı");
                response.put("student", student);
                response.put("programs", programs);
                response.put("program_count", programs.size());
            } else {
                response.put("success", false);
                response.put("message", "Email veya şifre hatalı");
            }

            conn.close();
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Database hatası: " + e.getMessage());
        }

        return response;
    }

    private static List<Map<String, Object>> getPrograms(Long studentId, Connection conn) throws SQLException {
        List<Map<String, Object>> programs = new ArrayList<>();

        String sql = "SELECT * FROM ogrenci_program_atamalari WHERE ogrenci_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1, studentId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Map<String, Object> program = new HashMap<>();
            program.put("id", rs.getLong("id"));
            program.put("ogrenci_id", rs.getLong("ogrenci_id"));
            program.put("program_id", rs.getLong("program_id"));
            program.put("atama_tarihi", rs.getString("atama_tarihi"));
            program.put("durum", rs.getString("durum"));
            program.put("notlar", rs.getString("notlar"));
            program.put("olusturan", rs.getString("olusturan"));
            programs.add(program);
        }

        return programs;
    }

    private static Map<String, Object> getStudentPrograms(Long studentId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            List<Map<String, Object>> programs = getPrograms(studentId, conn);

            response.put("success", true);
            response.put("data", programs);
            response.put("count", programs.size());
            response.put("student_id", studentId);

            conn.close();
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Database hatası: " + e.getMessage());
        }

        return response;
    }
}
