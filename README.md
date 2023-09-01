# blue-prints-be
Backend for Blue Prints Domain

# Getting Started

### Installation Instruction
* Java JDK 11
* Maven 3.9.x
* PostgresSQL

### Run Insert Role data into database
```postgresql
INSERT INTO public."role"
(id, created_by, created_on, updated_by, updated_on, "name")
VALUES(1000, '', '', '', '', 'ROLE_USER');

INSERT INTO public."role"
(id, created_by, created_on, updated_by, updated_on, "name")
VALUES(2000, '', '', '', '', 'ROLE_MODERATOR');

INSERT INTO public."role"
(id, created_by, created_on, updated_by, updated_on, "name")
VALUES(3000, '', '', '', '', 'ROLE_ADMIN');
```

### Run Spring Boot application
```
mvn clean install 
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.15/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.15/maven-plugin/reference/html/#build-image)

