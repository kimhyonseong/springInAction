# JMS로 메시지 전송하기

### 의존성
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-artemis</artifactId>
        <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-activemq</artifactId>
        <version>2.7.8</version><!-- 버전 없으면 에러 -->
    </dependency>
</dependencies>
```

```yml
spring:
  jms:
    template:
      default-destination: tacocloud.order.qeue
  artemis:
    user: tacoweb
    password: 1234
```

### 전송
1. 전송 목적지 정하기
2. createObjectMessage 메소드를 이용해 Message 객체 생성
   - jms.send("목적지", session -> session.createObjectMessage(객체));
3. convertAndSend 메소드를 통해 더 간단하게 만들수 있음
   - jms.convertAndSend("목적지",객체);

### 수신(pull 모델)
- send - receive 원시 메시지 수신
- convertAndSend - receiveAndConvert

### 수신(push 모델)
- 메소드에 @JmsListener("목적지") 애노테이션
- 목적지에 메시지가 들어오기를 기다림
- 도착하면 해당 메소드가 자동 실행
- 다수의 메시지를 빠르게 처리 가능 -> 속도에 맞춰서 처리 못하면 병목현상 유발

