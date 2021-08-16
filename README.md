Add monitoring (Prometheus and Grafana) to spring boot K8S
---------------------------------------------
https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/

https://www.mokkapps.de/blog/monitoring-spring-boot-application-with-micrometer-prometheus-and-grafana-using-custom-metrics

https://www.tutorialworks.com/spring-boot-prometheus-micrometer/

https://www.javadevjournal.com/spring-boot/spring-boot-actuator-with-prometheus/

----------------------------------------------------------------

update pom.xml and application.properties

Build and Start application

mvn -U clean compile package

java -jar target/kubernetes01-1.jar

Browser http://localhost:8080/actuator and http://localhost:8080/actuator/prometheus

-------------------------------------------------------------------------

Adding custom dependency in pom.xml, Application.java and Controller class

Build start and execute get persons

mvn -U clean compile package

java -jar target/kubernetes01-1.jar

Browser http://localhost:8080/persons and http://localhost:8080/actuator/prometheus

In Prometheus link search for Person

----------------------------------------------------------------------------

K8S SETUP

Updated pom.xml, application.properties, person_deployment.yaml, ingress.yaml , PersonController, and bean in Application.java

mvn -U clean compile package

eval $(minikube docker-env)

docker build -t springboot-docker-k8s-monitoring .

docker image ls

kubectl apply -f minikubeassets/person_deployment.yaml

kubectl apply -f minikubeassets/ingress.yaml

kubectl get pod,svc,ingress

curl -X GET http://person.com/actuator/prometheus  and curl -X GET http://person.com/persons

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm repo update

kubectl get pods -o wide

person-6cfd7ffc76-k7tt9                                        1/1     Running   0          150m   172.17.0.2    

update the above IP in extraScrapeConfigs.yaml

helm delete springboot-k8s-prometheus

helm install -f minikubeassets/extraScrapeConfigs.yaml springboot-k8s-prometheus prometheus-community/kube-prometheus-stack

update person_deployment.yaml

kubectl delete -f minikubeassets/person_deployment.yaml

kubectl delete -f minikubeassets/ingress.yaml

kubectl apply -f minikubeassets/person_deployment.yaml

kubectl apply -f minikubeassets/ingress.yaml

Browser: http://person.com/targets


-------------------------------------------------------------

Grafana

docker run -d --name=grafana -p 3000:3000 grafana/grafana

wait for 2 min

http://localhost:3000 admin/admin

Under add datasource select prometheus inside grafana

name=personsmonitoring

url as http://172.19.0.1:9090

Click Save and test button 

and verify Data source is working as response

Click + select dashboard > Add an empty panel

From dropdown on right side which has default "timeseries" search and select "graph"

Apply and save dashboard as "PersonsMonitoring"

Click "Edit" under "Panel Title"

Select "personsmonitoring" under Data source dropdown

Under "metrics_browser" add String "getPersons_time_seconds_count" and select Apply



 





