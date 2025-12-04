# SPRING ADVANCED

이 프로젝트는 Spring 기반 웹 애플리케이션에서 ArgumentResolver 구현, 리팩토링(Early Return / if-else 제거 / Validation), N+1 문제 해결, 테스트 코드 작성 및 수정, 그리고 프로젝트 에러 분석 등을 학습하고 연습하기 위한 실습 과제입니다.


## 필수 구현 기능

---

| 레벨 | 기능 | 설명 |
|------|-----|-----|
Lv0 |	에러 분석 |	프로젝트 실행 불가 원인 분석 및 해결|
Lv1 |	ArgumentResolver|	AuthUserArgumentResolver 복구|
Lv2	|코드 개선	Early Return
Lv2-1 | 불필요한 if-else 제거 |가독성 향상을 위해 else 블록 제거
Lv2-2 |Validation 적용
Lv3	|N+1 해결	|JPQL Fetch Join → EntityGraph 대체
Lv4|	테스트 코드	|총 4개의 테스트 수정 및 서비스 로직 수정
Lv5| API 로깅 | Interceptor와 AOP 구현


## Test Coverage
![](https://velog.velcdn.com/images/jheony/post/9bb92b84-7850-4d4b-80bf-99b6e5972951/image.png)

## 기술 스택
---
- Java: open-JDK 17
- Spring-boot: 3.5.7
- MySQL: 8.4
- IntelliJ version: 25.2.2
- API Test: Postman
- Testing : JUnit 5, Spring Boot Test
