# inspired by ysihaoy/scala-play

FROM anapsix/alpine-java:jdk8 as dev
LABEL maintainer="esplo@users.noreply.github.com"

ENV SBT_VERSION 1.1.0
ENV CHECKSUM ae7c5f904f27a887e6ab0392467dad59350e757a73d3e13a1a18a0d2fe8ce80d

# Install sbt
RUN apk add --update bash curl openssl ca-certificates ncurses && \
  curl -L -o /tmp/sbt.zip \
  https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.zip && \
  openssl dgst -sha256 /tmp/sbt.zip \
    | grep ${CHECKSUM} \
    || (echo 'shasum mismatch' && false) && \
  mkdir -p /opt/sbt && \
  unzip /tmp/sbt.zip -d /opt/sbt && \
  rm /tmp/sbt.zip && \
  chmod +x /opt/sbt/sbt/bin/sbt && \
  ln -s /opt/sbt/sbt/bin/sbt /usr/bin/sbt && \
  rm -rf /tmp/* /var/cache/apk/*

WORKDIR /app

EXPOSE 50051

CMD ["sbt", "; project app; ~reStart"]
