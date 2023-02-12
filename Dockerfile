FROM eclipse-temurin:11-jdk-focal
RUN apt-get update  \
 && apt-get install maven -y \
 && apt-get install -y vim
WORKDIR /app
COPY pom.xml /app
COPY botapeer-openapi /app/botapeer-openapi
RUN cd botapeer-openapi/spring && \
mvn clean package && \
mvn install:install-file -Dfile=target/openapi-spring-1.0.0.jar \
-DgroupId=org.openapitools \
-DartifactId=openapi-spring \
-Dversion=1.0.0 \
-Dpackaging=jar \
-DlocalRepositoryPath=/root/.m2/repository
COPY . /app
CMD ["./mvnw", "spring-boot:run"]