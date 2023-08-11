#This is to tell which java version to use and take the image from docker hub
FROM openjdk:20

#Copying our jar file to docker container, from target folder to /usr/app/ is the default location in Docker VM
#A docker container will be created and the path /user/app/ is available, we will copy the jar image to that location
COPY target/This-will-be-my-jar-fileName.jar /usr/app/
#We can write *.jar, so that in future if the jar name changes, it will dynamically pic up the jar
#COPY target/*.jar /usr/app/

#Now Setting the work directory where our jar file is located
WORKDIR /usr/app

#Now we will specify the entry point for our jar file to be executed (just like we execute in local by command java -jar jarFileName.jar)
ENTRYPOINT ["java","-jar","This-will-be-my-jar-fileName.jar"]
#We can write like below also
#ENTRYPOINT exec java $JAVA_OPTS -jar This-will-be-my-jar-fileName.jar

#now move to the project folder where docker file is located and use the below commands in cmd to build the docker image
#docker build -t nameOfTheImageYouLike .
#We can use this also
#docker build -t tagName/nameOfTheImageYouLike:1.0.0 . (tagname is your username for dockerHub, 1.0.0 is the version name)
#-t denoted tag name(image name)
#. denotes check the current directory of our project for the docker file and create an image using the instructions

#once the image is created we have to run the image in a container using below command, if we are using docker-compose.yml then we have to run docker compose up (see docker-compose.yml)
#docker run -d -p 9090:8080 imagename(nameOfTheImageYouLike in our case if we are not using tagName/nameOfTheImageYouLike:1.0.0)
#using the above way may create a container with random name, to create a container with your specified name, use the below way
#docker run -d -p 9090:8080 --name containername imagename(nameOfTheImageYouLike in our case if we are not using tagName/nameOfTheImageYouLike:1.0.0)
#-d means detached mode, means when this command is executed the cmd is free to do other tasks, else it will start showing thr logs direclty for our app
#-p means port mapping, the image will run in a container and we will map the port of the container to our local,
#so that we can access it from our local, here first is the host/local port and the second id the container port where our container is running
#We can do the port mapping in docker file too
#ENV PORT 8080
#EXPOSE 8080

#We can access the project in two ways, either from the localhost or from the docker,
#http://localhost:9090/RestEndpoint OR http://host.docker.internal:9090/RestEndpoint

#ip when the docker container is started :8080/RestEndpoint

#Now we wil push the docker image to dockerhub
#open terminal and type docker login, input your username and password that you used to create the account in docker hub

#We will check if the image is present or not using docker -images

#we will give a tag to our docker image so that we can easily understand our image in the hub, here rakesh is the tag name
#docker tag imagename.jar rakesh/imagename.jar (rakesh/nameOfTheImageYouLike.jar)

#Now if we again check the images, we can see that we have another image which contains the tag name too

#Now we will push the image to docker hub, here 1.0.0 is the version number
#docker push newTaggedImage(rakesh/nameOfTheImageYouLike:1.0.0)
#Now go to your dockerhub and under repositories we can see our tagged image, (here only we can find the pull command for our image)

#If we want to use other services along with our code like SQL or something else then we ahve to go firte Docker-compose file