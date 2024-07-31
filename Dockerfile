FROM eclipse-temurin:17

COPY . /epenal-backend

WORKDIR /epenal-backend

RUN ./gradlew clean bootJar

ARG ENV_PROFILE

ENV ENV_VAL=${ENV_PROFILE}

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${ENV_VAL}", "-Depenal.message=epenal", "-jar","build/libs/epenal-backend.jar"]
