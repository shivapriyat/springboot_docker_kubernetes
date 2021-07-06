Running Springboot with docker, kubernetes and ingress

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

// create namespace and PV and create elastic statefulset in kube-logging 


kubectl create namespace kube-logging

kubectl apply -f minikubeassets/elasticpv.yaml


docker pull docker.elastic.co/elasticsearch/elasticsearch:7.2.0

docker pull docker.elastic.co/kibana/kibana:7.2.0

kubectl apply -f minikubeassets/elasticsearch.yaml


kubectl get pods -n kube-logging

kubectl describe pod elasticsearch-0 -n kube-logging


// TEST ELASTIC SEARCH

kubectl get all -n kube-logging

service/elasticsearch   NodePort   10.102.137.166   <none>        9200:30550/TCP   2m2s

minikube ip --> 192.168.49.2

Browser: http://192.168.49.2:30550/_cat/indices?v

----

//create kibana in kube-logging

kubectl apply -f minikubeassets/kibana.yaml

kubectl get all -n kube-logging // wait for sometime till kibana is ready by checking logs

// TEST KIBANA

kubectl get all -n kube-logging

service/kibana          NodePort   10.103.130.184   <none>        5601:30661/TCP   3m41s

minikube ip --> 192.168.49.2

Browser: http://192.168.49.2:30661

---

// create fluentd in kube-logging

kubectl apply -f minikubeassets/fluentd.yaml

kubectl get pods --namespace=kube-logging

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


http://192.168.49.2:30550/_cat/indices?v


http://192.168.49.2:30550/logstash-2021.07.06/_search?pretty&from=1&size=100

Search for log statement "Request to get"


http://192.168.49.2:30661


// create index in kibana


Discover > Index Patterns > enter logstash-*


search for PersonController > logs from Controller class are seen

cleanup


kubectl delete -f minikubeassets/person_deployment.yaml


kubectl delete -f minikubeassets/ingress.yaml

kubectl delete -f minikubeassets/elasticsearch.yaml

kubectl delete -f minikubeassets/fluentd.yaml

kubectl delete -f minikubeassets/kibana.yaml


docker rmi springboot-k8s
