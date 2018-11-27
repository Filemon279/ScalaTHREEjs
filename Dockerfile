# Pull base image
FROM ingensi/play-framework:latest

# Define working directory
COPY . /root
WORKDIR /root
RUN sbt compile 


EXPOSE 9000
CMD ["sbt run"]
