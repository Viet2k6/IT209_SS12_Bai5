FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Sao chép tệp JAR đã được đóng gói từ Job test
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
