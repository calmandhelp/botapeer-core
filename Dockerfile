FROM eclipse-temurin:11-jdk-focal
RUN apt-get update  \
 && apt-get install maven -y \
 && apt-get install -y vim
 #&& apt-get install -y locales
#RUN localedef -i ja_JP -f UTF-8 ja_JP.UTF-8
#RUN echo 'LANG="ja_JP.UTF-8"' >  /etc/locale.conf
#ENV LANG ja_JP.UTF-8
WORKDIR /app
COPY . /app
CMD ["./mvnw", "spring-boot:run"]