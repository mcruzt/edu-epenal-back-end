FROM eclipse-temurin:17

COPY . /epenal-backend

WORKDIR /epenal-backend

RUN ./gradlew clean bootJar

EXPOSE 8080

ENV ENV_PROFILE=$ENV_PROFILE

ENTRYPOINT ["java", "-Dspring.profiles.active=${ENV_PROFILE}", "-Depenal.message=epenal", "-jar","build/libs/epenal-backend.jar"]
