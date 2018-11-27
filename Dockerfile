FROM ysihaoy/scala-play:2.12.2-2.6.0-sbt-0.13.15


# sbt

ENV SBT_URL=https://dl.bintray.com/sbt/native-packages/sbt
ENV SBT_VERSION 1.2.1
ENV INSTALL_DIR /usr/local
ENV SBT_HOME /usr/local/sbt
ENV PATH ${PATH}:${SBT_HOME}/bin

# Install sbt
RUN apk add --no-cache --update bash wget && mkdir -p "$SBT_HOME" && \
    wget -qO - --no-check-certificate "https://dl.bintray.com/sbt/native-packages/sbt/$SBT_VERSION/sbt-$SBT_VERSION.tgz" |  tar xz -C $INSTALL_DIR && \
    echo -ne "- with sbt $SBT_VERSION\n" >> /root/.built

# Install git
RUN  apk add --no-cache git openssh

# Install node.js
RUN apk add nodejs

# copy code
#&& sbt test:compile
COPY . /root/app/
WORKDIR /root/app
RUN sbt compile 

EXPOSE 9000
CMD ["sbt run"]

