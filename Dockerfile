FROM maven:3.5-jdk-8 AS build
COPY src /usr/quiz-question-service/src
COPY pom.xml /usr/quiz-question-service
RUN mvn -f /usr/quiz-question-service/pom.xml clean package
EXPOSE 8082
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017/glarimy","-jar","/usr/quiz-question-service/target/quiz-question-service-1.0.0.jar"]