템플릿엔진 : thymeleaf  

주의사항  
jsp 사용시엔 jar이 아닌 war로 생성후 종전에 서블릿 컨테이너를 설치해야함

궁금한것 & 해결
---
2023 03 29  
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
- SessionAttributes
  - 모델 정보를 HTTP 세션에 저장해주는 애노테이션