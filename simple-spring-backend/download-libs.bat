@echo off
echo Downloading required libraries...

mkdir libs 2>nul

echo Downloading MySQL Connector...
curl -L -o libs/mysql-connector-java-8.0.33.jar "https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar"

echo Downloading Gson...
curl -L -o libs/gson-2.10.1.jar "https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar"

echo Done! Libraries downloaded to libs/ folder.
pause
