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

2034 04 19
---
프로파일 활성화는 다양한 방법으로 가능(확실히 이해하진 못함) - yml / jvm / 환경변수 등  
ex) export SPRING_PROFILES_ACTIVE=prod