# Этап сборки
FROM gradle:8.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

# Этап запуска тестов
FROM builder AS tester

# Устанавливаем PostgreSQL
RUN apt-get update && \
    apt-get install -y postgresql postgresql-client && \
    rm -rf /var/lib/apt/lists/*

COPY src/test/resources/dump.sql /docker-entrypoint-initdb.d/

# Запускаем тесты с БД
CMD service postgresql start && \
    while ! pg_isready -h localhost -U postgres; do sleep 1; done && \
    gradle test --no-daemon

# Финальный образ (без тестов)
FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]