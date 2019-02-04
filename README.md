### Introduction

This project contains the code and instructions for running REST API for Customer Profile.

#### <a href="build-api"></a> Build the API

* Clone this repo into a directory `clone https://github.com/usmansaleemch/customer-profile.git `
* Change directory to the newly cloned repo: `cd customer-profile`

#### <a href="launch-api"></a> Run API

* Run Customer Profile API `./mvnw spring-boot:run`

Once cloned this application can be run as a spring boot application (main method is in the
au.com.ip.api.customerprofiles.CustomerProfilesApplication class).  The application can be run with 
the following system properties:

```
-Dspring.profiles.active=dev|staging|prod
```

An example command line to run the Customer Profile Service after it has been build would be (assuming the environment of DEV):

```
${JAVA_HOME}/bin/java -Dspring.profiles.active=dev - jar target/customer-profiles-1.0.0.jar
```
#### <a href="launch-api"></a> Test API

* Test Customer Profile API `./mvnw test`

#### API Contract Definition

Customer Profile micro services integrates Swagger UI; visit following endpoint to try out endpoints
http://localhost:8999/swagger-ui.html
[http://localhost:8999/swagger-ui.html](http://localhost:8999/swagger-ui.html).

Note: Api contract definition placed at /resources/api folder. 

#### <a href="uber-api"></a> Creating Uber(fat) Jar

To create an uber jar; execute the following command
```
mvn package
```
To run the application use the java -jar command as follows 

```
java -jar target/customer-profiles-1.0.0.jar
```

#### Quit Application 
To exit the application; press 
```ctrl + c```

#### Health Endpoint

Customer Profile microservices exposes an endpoint to determine the health of the system. It provides a 
basic UP / DOWN status. The url to determine the health of a locally running service is
 [http://localhost:8999/actuator/health](http://localhost:8999/actuator/health).

#### Swagger Endpoint

Customer Profile microservices integrates Swagger UI; visit following endpoint to try out endpoints
http://localhost:8999/swagger-ui.html
[http://localhost:8999/swagger-ui.html](http://localhost:8999/swagger-ui.html).

#### API Rest Examples
##### Post
```
curl -X POST "http://localhost:8999/profiles" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\":\"Usman\", \"lastName\":\"Chodhary\", \"dateOfBirth\":\"1983-02-04\", \"addresses\":[\t \t{\t \t\t\"type\":\"OFFICE\",\t \t\t\"address1\":\"549 Queen St. Brisbane\",\t \t\t\"preferred\": true\t \t},\t \t{\t \t\t\"type\":\"HOME\",\t \t\t\"address1\":\"9 Cadell St Toowong\",\t \t\t\"postCode\": \"4066\"\t \t} \t]}"
```
##### GET 
```
curl -X GET "http://localhost:8999/profiles/429049d3-d365-4267-bd4f-6d3e5cb010a9" -H "accept: */*"
```



  