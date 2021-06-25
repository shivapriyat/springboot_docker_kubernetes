Running Springboot

cd springboot_docker_kubernetes


mvn -U clean compile package


//java -jar target/kubernetes01-1.jar

docker build -t  springboot-docker .

docker image ls

docker run --name springboot-docker-container -p 8080:8080 -d springboot-docker


docker ps


//CHECK if Started Application line comes on executing the below command


docker logs springboot-docker-container



http://localhost:8080/persons


 browser
 http://localhost:8080/swagger-ui.html

 POST JSON


 {


  "age": 29,


  "firstName": "shivapriya",  


  "lastName": "t"


}  


PUT JSON


 {


  "age": 30,


  "firstName": "shivapriya",  


  "lastName": "tm"


}


cleanup


docker stop springboot-docker-container


docker rm springboot-docker-container


docker rmi springboot-docker