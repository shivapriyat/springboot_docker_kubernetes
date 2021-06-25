package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}

}


/*
Steps to run
minikube start
mvn clean compile package
java -jar target/kubernetes01-1.jar
//In separate terminal
docker build -t  springboot-k8s .
docker image ls
docker run --name springboot-k8s-container -p 8080:8080 -d springboot-k8s
http://localhost:8080/persons
http://localhost:8080/swagger-ui.html
docker stop springboot-k8s-container
docker rm springboot-k8s-container
docker rmi springboot-k8s

// in terminal1
eval $(minikube docker-env)
docker build -t  springboot-k8s .
minikube addons enable ingress
minikube tunnel
kubectl apply -f minikubeassets/person_ingress_deployment.yaml
kubectl apply -f minikubeassets/linux_ingress.yaml
minikube ip
sudo vi /etc/hosts ---> 192.168.49.2   person.com

curl -X GET http://person.com/persons

*/


