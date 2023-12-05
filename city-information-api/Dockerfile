FROM openjdk:21-jdk-slim

# Set working directory for the app in the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified working directory
COPY target/city-information-api-0.0.1-SNAPSHOT.jar /app/city-information-api-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "city-information-api-0.0.1-SNAPSHOT.jar"]
