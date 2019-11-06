FROM maven:3.5-jdk-8 AS build
COPY src /usr/glarimy_question/src
COPY pom.xml /usr/glarimy_question
RUN mvn -f /usr/glarimy_question/pom.xml clean package
EXPOSE 8082
ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:mysql://mysqldb:27017/glarimy?useSSL=false&allowPublicKeyRetrieval=true","-jar","/usr/glarimy_question/target/quiz-question-service-0.0.1-SNAPSHOT.jar"]