Running Springboot

cd springboot_docker_kubernetes
mvn -U clean compile package
 java -jar target/kubernetes01-1.jar

 browser
 http://localhost:8080/swagger-ui.html

 POST JSON
 {
  "age": 29,
  "firstName": "shivapriya",  
  "lastName": "t"
}