# Stage 1: Build the backend application using Maven and JDK 17
# This stage compiles the source code and packages it into a JAR file.
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copy only the pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Final runtime image using a slim JRE for efficiency and security
# We use openjdk:17-jre-slim to minimize the attack surface and image size.
FROM openjdk:17-jre-slim
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Environment variables for configuration
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Define the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
