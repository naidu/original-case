# The trip planner

## Project Strucute
The project as three folders
    Docker
    Original-case
    simple-travel-api-mock

The `Original-case` contains the web frontend which is written using angular with webpack whereas `simple-travel-api-mock` contains the api provided by the backend.  

The `Docker` folder contains the docker scripts which when run will do the following 
  1. Launch the node docker container
  2. Install required packages to run spring boot application
  3. Create three terminals inside a screen session
  4. Launch `simple-travel-api-mock` Spring Boot application in terminal 1
  5. Launch `Original-case`Spring Boot application in terminal 2
  6. Display the IP and port number of backend and frontend in terminal 3

Please note that it takes a while to start the Spring Boot application.  Therefore if webpage is not accessible after IP and port numbers are showin in terminal 3, wait a while and retry.  

## Starting the docker container

To run the whole project, all you need to do is, launch the startup script which is `launch_klm_docker.sh`.  
Starting with cloning the repo.  From repo directory issue below commands - 

    $ cd Docker
    $ ./launch_klm_docker.sh

At this point, the building of custom docker image will begin.  Post which, installation of dependent packages and building of angular app.  Once these steps are successful, the dokcer container is launched, three screen sessions are launched and you are put in 3rd screen by default.  Here you will see a wait bar and after some seconds, the IP and port number of the services are displayed.  

Now go to the IP:port number of the web app and try the things out.  
