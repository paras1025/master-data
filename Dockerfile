FROM openjdk:21-slim AS base

# Install necessary packages
RUN apt-get install -y tzdata

ENV TZ=Asia/Kolkata

WORKDIR /app

# Copy the built jar file
COPY master-api/target/service.jar .

# Expose the application port
EXPOSE 8080

CMD ["java", "-jar", "service.jar"]
