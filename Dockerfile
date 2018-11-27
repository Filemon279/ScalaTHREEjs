FROM ysihaoy/scala-play:2.12.2-2.6.0-sbt-0.13.15

# copy code
#&& sbt test:compile
COPY . /root/app/
WORKDIR /root/app
RUN sbt compile 

EXPOSE 9000
CMD ["sbt run"]

