FROM openjdk:11
# choosing OL as docker image OS (base image)
#  image we are choosing it must be present in Docker HUB
# FROM instruction will pull the image from DOCKER HUB
#LABEL key="@gmail.com"
ADD ./target/salon-service-0.0.1.jar /usr/src/salon-service-0.0.1.jar
#ARG JAR_FILE_NAME
#ARG PORT

#VOLUME /tmp

#RUN  mkdir  /mydocker
#  creating directory to copy code here

#COPY /target/salon-service-0.0.1-SNAPSHOT.jar  salon-service-0.0.1-SNAPSHOT.jar
#  copy data from Docker client to Docker Engine
# under that image -- that is getting build

WORKDIR  usr/src
# changing directory

ENTRYPOINT ["java","-jar","salon-service-0.0.1.jar"]
#CMD java -jar app.jar
#it will replace by parent process but entrypoint not
# So this is for running code
# BUt only as parent process
# CMD is going to decide default parent process for
# this new image
#EXPOSE ${PORT}

#docker build -t salon-service:v1 --build-arg JAR_FILE_NAME=salon-service-0.0.1.jar --build-arg PORT=8080 . --> building the image
#docker tag salon-service:v1 pankajsinha/salon-service:v1 --> tag the docker image
#docker push pankajsinha/salon-service:v1 --> push to public or private repo