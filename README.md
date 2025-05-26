# Rehber Hoca Mobile Android Application

A comprehensive Android mobile application for the Rehber Hoca education platform, allowing students to log in and view their assigned courses.

## 📱 Features

- **Student Authentication**: Secure login with email and password
- **Course Management**: View assigned courses with details
- **Modern UI**: Material Design components with Turkish language support
- **Network Integration**: REST API integration with Retrofit
- **Offline Support**: SharedPreferences for session management
- **Error Handling**: Comprehensive error handling and user feedback

## 🏗️ Architecture

- **MVVM Pattern**: Clean architecture with separation of concerns
- **Retrofit**: Network layer for API communication
- **Material Design**: Modern and intuitive user interface
- **SharedPreferences**: Local data storage for user sessions

## 📋 Project Structure

```
app/src/main/java/com/example/rehberhoca/
├── activities/
│   ├── BaseActivity.java          # Base activity with common functionality
│   ├── LoginActivity.java         # Student login screen
│   ├── DashboardActivity.java     # Main dashboard with courses
│   └── MainActivity.java          # Splash screen and navigation
├── adapters/
│   └── CoursesAdapter.java        # RecyclerView adapter for courses
├── models/
│   ├── Student.java               # Student data model
│   ├── Course.java                # Course data model
│   ├── LoginRequest.java          # Login request model
│   ├── LoginResponse.java         # Login response model
│   └── ApiResponse.java           # Generic API response model
├── network/
│   ├── ApiService.java            # Retrofit API interface
│   ├── ApiClient.java             # Retrofit client configuration
│   └── NetworkUtils.java          # Network utility functions
└── utils/
    ├── Constants.java             # Application constants
    ├── ValidationUtils.java       # Input validation utilities
    └── SharedPrefsManager.java    # SharedPreferences manager
```

## 🔧 Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK API 24 or higher
- Java 11 or higher
- Internet connection for API calls

### Installation

1. **Clone or download the project**
   ```bash
   git clone <repository-url>
   cd rehberhoca
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory and select it

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync Gradle
   - Click "Sync Now" to download dependencies

4. **Configure Backend URL**
   - Open `app/src/main/java/com/example/rehberhoca/network/ApiClient.java`
   - Update the `BASE_URL` constant:
     ```java
     // For Android Emulator
     private static final String BASE_URL = "http://10.0.2.2:8080/";
     
     // For physical device (replace with your computer's IP)
     private static final String BASE_URL = "http://192.168.1.XXX:8080/";
     
     // For production
     private static final String BASE_URL = "https://your-domain.com/";
     ```

5. **Run the Application**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio

## 🌐 Backend Integration

### Required API Endpoints

The mobile app expects the following REST API endpoints:

#### 1. Student Login
```
POST /api/mobile/login
Content-Type: application/json

Request Body:
{
    "email": "student@example.com",
    "sifre": "password123"
}

Response:
{
    "success": true,
    "message": "Giriş başarılı",
    "student": {
        "id": 1,
        "adSoyad": "Ahmet Yılmaz",
        "email": "student@example.com",
        "telefon": "05551234567",
        "kayitTarihi": "2024-01-15T10:30:00",
        "aktif": true
    },
    "token": "jwt-token-here" // Optional
}
```

#### 2. Get Student Courses
```
GET /api/mobile/student/{studentId}/courses

Response:
[
    {
        "id": 1,
        "ad": "Java Programlama Temelleri",
        "aciklama": "Java programlama dilinin temel kavramları",
        "egitmen": "Dr. Mehmet Özkan",
        "program": "Hafta içi Akşam",
        "baslangicTarihi": "2024-02-01T18:00:00",
        "bitisTarihi": "2024-04-30T21:00:00",
        "aktif": true
    }
]
```

#### 3. Get Student Profile
```
GET /api/mobile/student/profile/{studentId}

Response:
{
    "id": 1,
    "adSoyad": "Ahmet Yılmaz",
    "email": "student@example.com",
    "telefon": "05551234567",
    "kayitTarihi": "2024-01-15T10:30:00",
    "aktif": true
}
```

### Spring Boot Backend Example

A sample Spring Boot controller is provided in `backend-example/MobileApiController.java`. This controller demonstrates how to implement the required endpoints.

## 🎨 UI Components

### Login Screen (`activity_login.xml`)
- Email input field with validation
- Password input field with toggle visibility
- Login button with loading state
- Error message display
- Forgot password link (placeholder)

### Dashboard Screen (`activity_dashboard.xml`)
- Welcome message with student name
- Courses list with RecyclerView
- Pull-to-refresh functionality
- Floating action button for refresh
- Empty state message
- Toolbar with menu options

### Course Item (`item_course.xml`)
- Course name and description
- Instructor information
- Program/schedule details
- Material Design card layout

## 🔐 Security Features

- Input validation for email and password
- Network security with HTTPS support
- Session management with SharedPreferences
- Error handling for unauthorized access
- Secure password input with visibility toggle

## 📱 Testing

### Test Credentials
For testing purposes, you can use these sample credentials:
- Email: `test@rehberhoca.com`
- Password: `123456`

### Network Testing
- Test with Android Emulator using `10.0.2.2:8080`
- Test with physical device using your computer's IP address
- Test offline scenarios and error handling

## 🚀 Deployment

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### APK Location
Generated APKs will be in `app/build/outputs/apk/`

## 🔧 Configuration

### Network Timeout
Modify timeout values in `ApiClient.java`:
```java
.connectTimeout(30, TimeUnit.SECONDS)
.readTimeout(30, TimeUnit.SECONDS)
.writeTimeout(30, TimeUnit.SECONDS)
```

### App Colors
Customize colors in `app/src/main/res/values/colors.xml`:
```xml
<color name="primary_color">#2196F3</color>
<color name="primary_dark">#1976D2</color>
<color name="accent_color">#FF4081</color>
```

### Text Strings
All text strings are in `app/src/main/res/values/strings.xml` for easy localization.

## 🐛 Troubleshooting

### Common Issues

1. **Network Connection Failed**
   - Check if backend server is running
   - Verify the BASE_URL in ApiClient.java
   - Ensure device/emulator has internet access

2. **Build Errors**
   - Clean and rebuild project: `Build > Clean Project`
   - Sync Gradle files: `File > Sync Project with Gradle Files`
   - Check Android SDK and build tools versions

3. **Login Failed**
   - Verify backend API is responding
   - Check request/response format
   - Enable logging in ApiClient to debug

### Debug Logging
Enable detailed network logging by setting the logging level in `ApiClient.java`:
```java
loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
```

## 📄 License

This project is part of the Rehber Hoca education platform.

## 👥 Support

For technical support or questions, please contact the development team.
