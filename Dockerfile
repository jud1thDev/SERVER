# Dockerfile

# jdk17 Image Start
FROM openjdk:17

# 인자 설정 및 jar 파일 복제
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]