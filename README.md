Running Springboot with docker and Kubernetes

cd springboot_docker_kubernetes


minikube start


minikube addons enable ingress 


check if ingress-nginx controller is running with cmd: kubectl get pods --all-namespaces


in separate terminal execute minikube tunnel



eval $(minikube docker-env)


mvn -U clean compile package


//java -jar target/kubernetes01-1.jar


docker build -t  springboot-k8s .


docker image ls


kubectl apply -f minikubeassets/person_deployment.yaml


kubectl apply -f minikubeassets/ingress.yaml



kubectl get pods,svc,ingress

pod/person-56884754b9-sg6lc                   1/1     Running   0          4m35s


service/person-service    ClusterIP   10.103.119.194   <none>        8080/TCP   4m35s


ingress.networking.k8s.io/person-ingress   <none>   person.com   192.168.49.2   80      4m22s


//CHECK if Started Application line comes on executing the below command


 kubectl logs person-56884754b9-sg6lc


 minikube ip

 result:   192.168.49.2


 sudo vi /etc/hosts
 

Add below line:


192.168.49.2  person.com


TESTING:



curl -X GET http://person.com/persons


curl -X POST -H "Content-Type: application/json" --data '{"age": 30,"firstName":"Shivapriya", "lastName":"t"}' http://person.com/persons


curl -X PUT -H "Content-Type: application/json" --data '{"age": 28,"firstName":"Shivapriya", "lastName":"t"}' http://person.com/persons/3


curl -X DELETE http://person.com/persons/3


cleanup


kubectl delete -f minikubeassets/person_deployment.yaml


kubectl delete -f minikubeassets/ingress.yaml


docker rmi springboot-k8s