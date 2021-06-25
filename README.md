Running Springboot with docker, kubernetes, ingress and helm

cd springboot_docker_kubernetes



// RENAME helmscripts FOLDER and use it as reference while executing below steps



minikube start


minikube addons enable ingress 


check if ingress-nginx controller is running with cmd: kubectl get pods --all-namespaces


in separate terminal execute minikube tunnel



eval $(minikube docker-env)


mvn -U clean compile package


//java -jar target/kubernetes01-1.jar


docker build -t  springboot-k8s .


docker image ls


mkdir helmscripts


cd helmscripts


helm create springboothelm


tree springboothelm


edit values.yaml (image: repository: springboot-k8s tag: "latest"    and service port : 8080)


edit templates/deployment.yaml  (containerPort: 8080 and comment  livenessProbe and readinessProbe sections )

----
Add Ingress

helm repo add nginx-stable https://helm.nginx.com/stable

helm repo update

edit values.yaml (ingress enabled: true   className: "nginx"  - host: person.com   paths:  - path: /persons pathType: ImplementationSpecific)
-----
helm template springboothelm


helm lint springboothelm


helm install springboothelm --debug --dry-run springboothelm


helm install springboothelmrelease springboothelm


helm list -a


kubectl get pods,svc,ingress


//results

pod/springboothelmrelease-78f4b9c767-lhf2d   1/1     Running   0          34s


service/springboothelmrelease   ClusterIP   10.99.23.35   <none>        8080/TCP   36s


ingress.networking.k8s.io/springboothelmrelease   nginx   person.com             80      36s


//CHECK if Started Application line comes on executing the below command


 kubectl logs springboothelmrelease-78f4b9c767-lhf2d


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

helm delete springboothelmrelease


docker rmi springboot-k8s