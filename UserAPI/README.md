# 우리들의 제목학원 2018<br/><small>User API 구성</small>

## Issues

- JUnit 단위 테스팅을 세분화하여 테스팅을 진행합니다.

    - 기존에는 Repository, Service, Controller 단위까지 완료하였습니다.
    
    - 이번에는 Domain, VO(Value Object), DTO(Data Transfer Object),  Form Model, Repository, Domain Service, Controller 단위 까지 테스팅을 진행합니다.
    
- Spring Security + JWT 를 이용한 사용자 인증 API 를 구축합니다.

- Spring Security 를 REST API 에서 효과적으로 이용할 수 있는 방안을 구축합니다.

- React.js Client 에서 기존 Spring Security 에서 Basic Cookie Token(Basic 으로 시작하는 토큰) 에서 JSON Web Token (통칭 JWT. Bearer 로 시작하는 토큰) 으로 대체할 수 있도록 합니다.

## Unit Structure

각 요소 옆의 소괄호는 main 내부에 있는 패키지 이름입니다.

- Domain Object (domain) : JPA 에서 데이터베이스와 접하는 객체로서 이를 그대로 이용하지 않고 DTO 로 변환하여 이용합니다.

- Value Object (vo) : GET 방식에서 객체를 조회할 때 오로지 읽기 전용으로 쓸 때 이용합니다.

- Model Object (model) : POST 방식에서 입력한 FORM 에 따른 DB의 객체 내용을 변경할 때 이를 이용하여 변동시킵니다. GET 방식에서는 입력 FORM 에서 필요할 때 변환 시켜줍니다.

- Data Transfer Object (dto) : Domain Object 를 이용한 데이터 조작을 더욱 안전하게 하기 위해 이 안에 있는 값을 Wrapping 시킵니다.

- Repository (repository) : JPA Repository. 기본적인 CRUD 제공합니다.

- Domain Service (domain_service) : Domain 객체에 대한 CRUD 를 Repository 에 있는 함수 중 일부를 추출하여 DB의 트랜젝션을 안전하게 유지합니다.

- Integrate Service (integrate_service) : Domain Service 에서 필요한 함수를 일부 추출하여 통합적으로 필요한 비즈니스 로직을 설정하여 Controller 에서 이용합니다.

- Controller (controller) : Spring Security 에서 사용자 별 영역을 구분하여 요청한 URI 에 따라 위에서 작성한 요소를 제공합니다.

## Environment

- Java : IntelliJ IDEA

- MySQL : MySQL Workbench

## Technology

- Maven 4.0.0

- Spring Framework 5

    - Spring Boot 2.0.3
    
    - Spring Web MVC 5.0.7
    
    - Spring Security 5.0.6

    - Spring Data JPA 2.0.3
    
- Relational Database

    - MySQL 5.7.2.0
    
- Security Framework

    - Java JWT 0.7
    
- Unit Testing

    - JUnit 4

## Maven pom.xml

`pom.xml` 를 기반으로 Maven Dependency 를 구성하여 Update Maven 은 필수입니다.

```
<dependencies>
    <!-- 1. Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- 2. Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <!-- 3. Spring Web MVC -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- 4. Java JWT Library -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.7.0</version>
    </dependency>
    <!-- 5. MySQL JDBC Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!-- 6. Lombok Project -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!-- 7. Spring Boot Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    <!-- 8. Spring Framework Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!-- 9. Spring Security Test -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Relational Database Structure

![pj_composition_user](/UserAPI/image/pj_composition_user.png)

## Schedule

스케쥴은 변경 사항이 있으면 갱신하겠습니다.

![UserAPI_Schedule](/UserAPI/image/UserAPI_Schedule.png)

## Author

- 강인성([tails5555](http://github.com/tails5555))