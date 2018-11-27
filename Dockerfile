$ cat target/docker/Dockerfile
FROM openjdk:latest
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
EXPOSE 9000
CMD ["sbt run"]

