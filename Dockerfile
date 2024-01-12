# First stage: Download dependencies
FROM maven:3.9.1-amazoncorretto-19 as dependencies
COPY pom.xml .
RUN mvn -B dependency:go-offline

FROM maven:3.9.1-amazoncorretto-19 as build
COPY --from=dependencies /root/.m2 /root/.m2
COPY pom.xml .
COPY lib lib
COPY src src
# Setup the maven cache
RUN mvn -B package -DskipTests

FROM openjdk:19-slim-buster
COPY --from=build lib/dd-java-agent-1.21.0.jar dd-java-agent.jar
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java", \
            "-javaagent:dd-java-agent.jar", \
            "-Ddd.service.name=helloworld", \
            "-Ddd.logs.injection=true", \
            "-Ddd.trace.sample.rate=1", \
            "-Ddd.profiling.enabled=true", \
            "-jar", "app.jar"]
