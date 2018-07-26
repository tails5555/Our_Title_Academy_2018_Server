# 우리들의 제목학원 2018<br/><small>User API 구성</small>

## Issues

- JUnit 단위 테스팅을 세분화하여 테스팅을 진행합니다.
    - 기존에는 Repository, Service, Controller 단위까지 완료하였습니다.
    - 이번에는 Domain, Value Object, Form Model, Repository, Domain Service, Controller 단위 까지 테스팅을 진행합니다.
- Spring Security + JWT 를 이용한 사용자 인증 API 를 구축합니다.
- Spring Security 를 REST API 에서 효과적으로 이용할 수 있는 방안을 구축합니다.
- React.js Client 에서 기존 Spring Security 에서 Basic Cookie Token(Basic ???) 에서 JSON Web Token (통칭 JWT) 로 대체할 수 있도록 합니다.

## Environments & Technology

- Spring Framework
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

```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.7.0</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
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

## Thanks To

## Author
- [강인성](http://github.com/tails5555)