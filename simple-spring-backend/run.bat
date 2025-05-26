@echo off
echo Starting Simple Spring Backend...

REM Download libraries if not exists
if not exist "libs\mysql-connector-java-8.0.33.jar" (
    echo Downloading libraries...
    call download-libs.bat
)

REM Compile
echo Compiling...
javac -cp "libs\*" SimpleApp.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

REM Run
echo Starting server...
java -cp ".;libs\*" SimpleApp

pause
