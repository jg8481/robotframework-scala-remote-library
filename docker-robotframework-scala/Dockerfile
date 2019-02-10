FROM openjdk:8-jdk-stretch
MAINTAINER Joshua Gorospe <joshua.gorospe@gmail.com>

USER root
ENV SCALA_VERSION 2.12.8
ENV SBT_VERSION 1.2.8

# No interactive frontend during docker build
ENV DEBIAN_FRONTEND=noninteractive \
    DEBCONF_NONINTERACTIVE_SEEN=true


# Un-comment the following two lines if you are not running this container in Amazon ECS or in Kubernetes.
#RUN mkdir /rfw
#COPY ./Workshop-Examples /rfw

RUN apt-get update --fix-missing -y && apt-get install --no-install-recommends -y && apt-get -y upgrade \
    apt-utils \
    ca-certificates \
    gcc \
    make \
    git \
    git-core \
    gettext \
&& mv /usr/bin/envsubst /usr/local/sbin/envsubst

RUN apt-get update && apt-get install --no-install-recommends -y
RUN apt-get -y upgrade

# Install Python 3, update pip3, and install all Robot Framework libraries
RUN apt-get update --fix-missing -y \
  && apt-get install python3 -y \
  && apt-get install python3-pip -y \
  && python3 -m pip install pip --upgrade \
  && python3 -m pip install wheel \
  && python3 -m pip install robotframework \
  && pip3 install pyyaml \
  && apt-get clean -y \
  && apt-get update --fix-missing -y \
  && apt-get install build-essential libssl-dev libffi-dev -y \
  && pip3 install -U robotframework-pabot

RUN apt-get update --fix-missing -y && apt-get install --no-install-recommends -y && apt-get -y upgrade \
  wget \
  curl \
  libcurl3

RUN apt-get update --fix-missing -y && apt-get install --no-install-recommends -y && apt-get -y upgrade \
  clang \
  libunwind-dev \
  libgc-dev \
  libre2-dev

# Install Scala
RUN \
  curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /root/.bashrc

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion && \
  mkdir project && \
  echo "scalaVersion := \"${SCALA_VERSION}\"" > build.sbt && \
  echo "sbt.version=${SBT_VERSION}" > project/build.properties && \
  echo "case object Temp" > Temp.scala && \
  sbt compile && \
  rm -r project && rm build.sbt && rm Temp.scala && rm -r target

EXPOSE 8270

ADD commands-running-inside-a-docker-container.sh /usr/local/bin/commands-running-inside-a-docker-container.sh
RUN chmod +x /usr/local/bin/commands-running-inside-a-docker-container.sh
