#This is to tell which java version to use
FROM openjdk:20

#Copying our jar file to docker container, from target folder to /usr/app/ is the default location in Docker VM
#A docker container will be created and the path /user/app/ is available, we will copy the jar image to that location
COPY target/This-will-be-my-jar-fileName.jar /usr/app/

#Now Setting the work directory where our jar file is located
WORKDIR /usr/app

#Now we will specify the entry point for our jar file to be executed (just like we execute in local by command java -jar jarFileName.jar)
ENTRYPOINT ["java","-jar","This-will-be-my-jar-fileName.jar"]

#now move to the project folder where docker file is located and use the below commands in cmd to build the docker image
#docker build -t nameOfTheImageYouLike .
#-t denoted tag name(image name)
#. denotes check the current directory for the docker file and create an image using the instructions

#once the image is created we have to run the image using below command
#docker run -d -p 8080:8080 imagename(nameOfTheImageYouLike in our case)
#-d means detached mode, means when this command is executed the cmd is free to do other tasks, else it will start showing thr logs direclty for our app
#-p means port mapping, the image will run in a container and we will map the port of the container to our local,
#so that we can access it from our local, here first is the host port and dsecond id the container port
