# Use an official Java runtime as a parent image
FROM amazoncorretto:21.0.4-alpine3.18

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/userDetails-details-1.0.0.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]