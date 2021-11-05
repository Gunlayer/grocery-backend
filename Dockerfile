# Run unit tests
FROM maven:3.8.3-jdk-11-slim AS tests
WORKDIR /home/app
COPY . ./
RUN mvn test

# SonarQube Cloud analyses
FROM tests AS sonarscan
WORKDIR /home/app
ARG SONAR_TOKEN
RUN mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
-Dsonar.projectKey=grocery-shop-service \
-Dsonar.organization=corporation-of-good \
-Dsonar.host.url=https://sonarcloud.io \
-Dsonar.login=2230c0e5a560570fef25de6494835227a3c962ff
# -Dsonar.login=${SONAR_TOKEN}


# Build stage
FROM sonarscan AS build
WORKDIR /home/app
RUN mvn -DskipTests clean package

# Deploy stage
FROM tomcat:9-jre11-openjdk-slim as runapp
# COPY ./resources/docker/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY --from=build /home/app/target/*.war /usr/local/tomcat/webapps
# CMD ["catalina.sh", "run"]
EXPOSE 8080
ENTRYPOINT catalina.sh run
