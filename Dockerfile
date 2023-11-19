# Start with a base image containing Java runtime
FROM maven:3.9.5-eclipse-temurin-21 as build

# Make source folder
RUN mkdir -p /workspace
WORKDIR /workspace

# Copy the POM
COPY pom.xml .

# Copy the source code
COPY src src