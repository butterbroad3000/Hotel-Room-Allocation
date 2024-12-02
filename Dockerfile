
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/

COPY . .

RUN chmod +x gradlew

RUN ./gradlew clean build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/HotelRoomAllocation-0.0.1-SNAPSHOT.jar"]

