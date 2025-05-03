# 1. OpenJDK 17 이미지를 베이스 이미지로 사용
FROM openjdk:17-jdk-slim

# 2. 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle Wrapper와 프로젝트 파일을 컨테이너로 복사
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

# 4. Gradle Wrapper에 실행 권한 부여
RUN chmod +x gradlew

# 5. Gradle을 사용하여 프로젝트 빌드 (테스트 제외)
RUN ./gradlew build -x test

# 6. 빌드된 JAR 파일을 실행
CMD ["java", "-jar", "build/libs/myapp.jar"]
