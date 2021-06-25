Running Springboot with docker and Kubernetes

cd springboot_docker_kubernetes


minikube start


eval $(minikube docker-env)


mvn -U clean compile package


//java -jar target/kubernetes01-1.jar


docker build -t  springboot-k8s .


docker image ls


kubectl apply -f minikubeassets/person_deployment.yaml


kubectl get pods,svc

// results example:

pod/person-56884754b9-b7pk5                   1/1     Running   0          109s



service/person-service    LoadBalancer   10.108.98.124   <pending>     8080:30001/TCP   10s


//CHECK if Started Application line comes on executing the below command


 kubectl logs person-56884754b9-b7pk5


 minikube ip

 192.168.49.2

TESTING:



curl -X GET http://192.168.49.2:30001/persons


curl -X POST -H "Content-Type: application/json" --data '{"age": 30,"firstName":"Shivapriya", "lastName":"t"}' http://192.168.49.2:30001/persons


curl -X PUT -H "Content-Type: application/json" --data '{"age": 28,"firstName":"Shivapriya", "lastName":"t"}' http://192.168.49.2:30001/persons/3


curl -X DELETE http://192.168.49.2:30001/persons/3


cleanup


kubectl delete -f minikubeassets/person_deployment.yaml