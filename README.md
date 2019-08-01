# spring-todo-list-api

  A restful backend service with spring framework. This project was initialized with * [SPRING INITIALIZR](https://start.spring.io/). Only included "Cloud OAuth2", "h2", "web", "security" and "jpa" packages. 

  This project is designed to be a microservice to communicate with this * [React Project](https://github.com/camelkyhn/todo-list-react). Make sure you have installed both and make them run together. 
	
This api project will be running on 
```
http://localhost:8080
```
And the frontend project will be running on 
```
http://localhost:3000
```
Then go checkout this route on your browser and you will discover more functionalities of the system visually.

### Prerequisites

  Make sure you have installed java on your machine firstly and also you need maven or gradle installed to run it or you can have them in some of ide's. Maven has plugins for eclipse and idea. Referenced from * [Part III. Using Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)

### Usage

#### If you are using Maven, 
```
$ mvn spring-boot:run
```
you can use this command to run the project easily on the project directory.

#### If you are using Gradle, 
```
$ gradle bootRun
```
you can use this command to run the project easily on the project directory.

#### After it runs,
it will insert some data to db, you can check the db out with the "h2 console" from
```
http://localhost:8080/h2-console
```
![alt text](http://www.h2database.com/html/images/quickstart-3.png)

##### But make sure you have entered "jdbc:h2:mem:todolist" to JDBC URL in the h2 db connection screen on your browser(user credentials are default, you can directly connect)
All these properties and values can be found on application.properties file(username, password etc.) in the project I've made them static for ease, but also could be get from environment values/files.
