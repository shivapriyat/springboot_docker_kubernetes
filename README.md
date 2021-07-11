Add monitoring (Prometheus and Grafana) to spring boot as docker containers
------------------------------------------------
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

DOCKER SETUP

docker network create monitoring

mvn -U clean compile package

docker build -t springboot-docker-monitoring .

docker image ls

docker run --network monitoring --name persons-monitoring -p 8080:8080 -d springboot-docker-monitoring:latest

docker pull prom/prometheus

docker image ls

docker network ls

docker network inspect monitoring

>>> Gateway 172.19.0.1 ",   >>>

edit prometheus.yml to point to Gateway address

docker run --network monitoring -v /home/priya/eclipse-workspace/may21/git/springboot_docker_kubernetes/src/main/resources/prometheus.yml:/etc/prometheus/prometheus.yml -p 9090:9090 -d prom/prometheus

docker ps

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

------------------------------------------------------------