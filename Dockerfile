FROM eclipse-temurin:11-jdk-focal
RUN apt-get update  \
 && apt-get install maven -y \
 && apt-get install -y vim
WORKDIR /app
COPY . /app
CMD ["./mvnw", "spring-boot:run"]