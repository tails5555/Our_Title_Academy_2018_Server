# 우리들의 제목학원 2018<br/><small>Context API 구성</small>

## Issues

- JUnit 단위 테스팅을 세분화하여 테스팅을 진행합니다.
    
    - 이는 구현이 완료되는 시점에서 테스팅을 진행하겠습니다.

- Redis Cache 를 이용하여 단위 시간 별로 실시간 베스트 기능, 오늘의 기능을 구현합니다.

- JPA 를 이용하여 상속 된 객체에 대하여 적용할 수 있게 데이터베이스의 구성을 형성합니다.

## Unit Structure

각 요소 옆의 소괄호는 main 내부에 있는 패키지 이름입니다.

- Domain Object (domain) : JPA 에서 데이터베이스와 접하는 객체로서 이를 그대로 이용하지 않고 DTO 로 변환하여 이용합니다.

- Value Object (vo) : GET 방식에서 객체를 조회할 때 오로지 읽기 전용으로 쓸 때 이용합니다.

- Model Object (model) : POST 방식에서 입력한 FORM 에 따른 DB의 객체 내용을 변경할 때 이를 이용하여 변동시킵니다. GET 방식에서는 입력 FORM 에서 필요할 때 변환 시켜줍니다.

- Data Transfer Object (dto) : Domain Object 를 이용한 데이터 조작을 더욱 안전하게 하기 위해 이 안에 있는 값을 Wrapping 시킵니다.

- Repository (repository) : JPA Repository. 기본적인 CRUD 제공합니다.

- Domain Service (domain_service) : Domain 객체에 대한 CRUD 를 Repository 에 있는 함수 중 일부를 추출하여 DB의 트랜젝션을 안전하게 유지합니다.

- Integrate Service (integrate_service) : Domain Service 에서 필요한 함수를 일부 추출하여 통합적으로 필요한 비즈니스 로직을 설정하여 Controller 에서 이용합니다.

- Controller (controller) : 요청, 제목, 댓글, 공감 등을 구분하여 요청한 URI 에 따라 위에서 작성한 요소를 제공합니다.

## Environment

- Java : IntelliJ IDEA

- MySQL : MySQL Workbench

- Redis : Window Embedded Service

## Technology

- Maven 4.0.0

- Spring Framework 5

    - Spring Boot 2.0.3
    
    - Spring Web MVC 5.0.7

    - Spring Data JPA 2.0.3
    
    - Spring Data Redis 2.0.3
    
- Relational Database

    - MySQL 5.7.2.0
    
- Redis 
    
    - Redis 3.2.0
    
    - Java Redis Client 2.9.0
    
- Unit Testing

    - JUnit 4

## Maven pom.xml

```
<dependencies>
    <!-- 1. Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- 2. Spring Web MVC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- 3. Spring WebSocket -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <!-- 4. MySQL JDBC Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!-- 5. Lombok Project -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!-- 6. Spring Boot Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    <!-- 7. Spring Framework Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!-- 8. Spring Data Redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- 9. Redis Client Driver -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>
    <!-- 10. Swagger RESTFul Document Library -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.6.1</version>
    </dependency>
    <!-- 11. Swagger UI -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.6.1</version>
    </dependency>
</dependencies>
```

## Relational Database Structure

![pj_composition_context](/ContextAPI/image/pj_composition_context.png)

## Schedule

스케쥴은 변경 사항이 있으면 갱신하겠습니다.

![pj_composition_context](/ContextAPI/image/ContextAPI_Schedule.png)

## Author

- 강인성([tails5555](http://github.com/tails5555))