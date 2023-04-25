템플릿엔진 : thymeleaf  

주의사항  
jsp 사용시엔 jar이 아닌 war로 생성후 종전에 서블릿 컨테이너를 설치해야함

2023 03 29
---
- DesignTacoController()에 IngredientRepository 클래스를 주입했지만 JdbcIngredientRepository 클래스가 자동으로 주입되는 이유는 IngredientRepository 클래스로 구현된 구현체가 JdbcIngredientRepository 밖에 없기 때문  
- 만약 IngredientRepository 구현체가 여러개일 경우에는 @Qualifier 애노테이션으로 해결 가능
```java
@Controller
public class DesignTacoController {
  public DesignTacoController(@Qualifier("jdbcIngredientRepository") IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }
}
```
- jdbcIngredientRepository 클래스에도 @Qualifier 추가해야함
```java
@Repository
@Qualifier("JdbcIngredientRepository")
public class JdbcIngredientRepository implements IngredientRepository{
}
```

2023 04 03
---
SessionAttributes
- 모델 정보를 HTTP 세션에 저장해주는 애노테이션

2023 04 06
---
컨버터 사용 이유 - com.example.tacocloud.web.IngredientByIdConverter
- id를 이용해 데이터베이스에 저장된 특정 식자재의 데이터를 읽고 Ingredient 객체로 변경하기 위해 사용

2023 04 12
---
[https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter) 참고  
[https://spring.io/guides/gs/authenticating-ldap/](https://spring.io/guides/gs/authenticating-ldap/) - ldap 참고  
ldap 오류로 인한 실행 불가

2023 04 19
---
프로파일 활성화는 다양한 방법으로 가능(확실히 이해하진 못함) - yml / jvm / 환경변수 등  
ex) export SPRING_PROFILES_ACTIVE=prod

2023 04 24
---
스테레오 타입 애노테이션 : 객체를 선언할 때 그 객체가 어떤 역할을 하는지 나타내는 애노테이션 / 스프링이 자동으로 감지하고 빈으로 등록  
@PostMapping(consumes="application/json") : Content-type이 application/json과 일치하는 요청만 처리  
@ResponseStatus(HTTPStatus.CREATED) : 요청이 성공적일 시 201 상태코드 전달  

2023 04 25
@RestResource(rel = "관계 이름",path = "경로") : api 경로와 관계 이름 설정 가능, 리소스의 객체에 애노테이션 추가 ex) Taco

이전
```
"tacoes" : {
    "href" : "http://localhost:8080/api/tacoes{?page,size,sort}",
    "templated" : true
}
```
이후
```
"tacos" : {  // 관계
    "href" : "http://localhost:8080/api/tacos{?page,size,sort}",  // 경로
    "templated" : true
}
```