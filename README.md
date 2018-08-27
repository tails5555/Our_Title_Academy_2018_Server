# 우리들의 제목 학원 2018 Server Side<br/><small>Our Title Academy with Spring Boot</small>

![ota_application_description](https://github.com/tails5555/Our_Title_Academy_2018_Server/blob/master/image/ota_application_description.png)

달랑 사진 한 장에 짧은 몇 글자만으로 사람들에게 웃음을 제공할 수 있는 Web Application 제작.

<h3>🐸 여러분들의 제목 센스와 드립을 <b>발휘해~</b> <i>주세요!!!</i></h3>

<h3>🙋 제목 학원 베타 버전을 구축하기 위해 잠깐 작업 중입니다.</h3>

<h3>👩‍🔧  NoticeAPI 기능에 대해서는 ContextAPI 단위 테스팅과 동시에 진행됩니다.</h3>

#### 💬 적극 반영하면 좋을 리뷰는 [Issues](https://github.com/tails5555/Our_Title_Academy_2018_Server/issues) 에 남겨주시면 감사하겠습니다.

<h4>Application 에 대한 평가 리뷰, 개선 사항 등을 이야기 해 주셔도 좋습니다. 😉</h4>

## Description

소프트웨어 캡스톤 디자인의 후속 프로젝트 진행을 위한 Web Application Server 제작.

아주 평범한 사진 한 장에 제목을 이용하여 다른 사용자들에게 웃음을 제공하는 Application.

최근 제목학원 App 의 성향 문제에 따른 문제점을 해결하기 위해 이 App 을 관리하는 매니저가 회원이 올린 사진을 허가해야 제목을 올리도록 하여 선정성을 최소한으로 줄이는 Application 을 제작합니다.

## Application Structure

Application 의 간략한 구성은 다음과 같습니다. Web 측에도 기재하였으니 참고 바랍니다.

![Our_Title_Academy_App_Structure](https://github.com/tails5555/Our_Title_Academy_2018_Server/blob/master/image/Our_Title_Academy_App_Structure.png)

## Server Structure

1. User REST API - 사용자 계정, 프로필 관리 서버

    - [참고 문서로 이동](https://github.com/tails5555/Our_Title_Academy_2018_Server/tree/master/UserAPI)
    
2. Context REST API - 제목학원 요청글, 사진, 제목, 반응, 댓글

    - [참고 문서로 이동](https://github.com/tails5555/Our_Title_Academy_2018_Server/tree/master/ContextAPI)

3. Notice REST API - 공지사항, 관리자 FAQ

    - [참고 문서로 이동](https://github.com/tails5555/Our_Title_Academy_2018_Server/tree/master/NoticeAPI)

## Web References

<h3>📰 React.js Web Application 은 아래에 접속하여 참고하시면 됩니다.</h3>

[Web 참고하기](https://github.com/tails5555/Our_Title_Academy_2018_Web)

## Using Technologies

- Relational Database : MySQL

- NoSQL Database : Redis 

- Security : Spring Security, JWT(JSON Web Token)

- Testing : JUnit, Mockito Mock MVC

- Web Socket : SockJS, STOMP (in React.js Application)

- RESTful API Documents : Swagger UI

- Data : Spring Data JPA, Spring Data Redis

## Author

- 강인성([tails5555](http://github.com/tails5555))

## Project History

- 2018 / 08 / 15 : 최초 public 공개. Alpha Version.

    - UserAPI, ContextAPI 까지 현재까지 진행한 내용들을 공개함.
    
- 2018 / 08 / 29 : Beta Version. (지연될 수 있음)

    - NoticeAPI 까지 최종 완성을 목표로 공개함.

## Thanks To

<h3>🙇‍♀️ Issues 에 모든 답글을 달아주신 분들을 여기에 작성 해 드립니다.</h3>

<h3>🤷‍♀️ Thanks To 에 비공개를 원한다면 반영하겠습니다.</h3>

- Redis 과 Scheduled Annotation 내용에 대해서 팁을 주신 조민국([MinGOODdev](http://github.com/MinGOODdev)) 형님.

- JPA 에서 데이터베이스 구조에 따른 참조의 성능 비교를 도달하기 위해 팁을 준 서종현([shouwn](http://github.com/shouwn)).

- File Upload 에 대한 로드 벨런싱의 아이디어를 제공해준 장승훈([wkdtndgns](https://github.com/wkdtndgns)).