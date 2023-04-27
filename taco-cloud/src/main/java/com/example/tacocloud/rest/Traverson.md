# Traverson
- traverse on - '돌아다닌다'는 의미로 붙여짐
- HATEOAS와 함께 제공
- 하이퍼미디어 API를 사용할 수 있는 솔루션(RestTemplate로는 구현하기 어려움)
- 리소스 쓰기, 삭제 불가

```java
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Traverson() {

  Traverson traverson = new Traverson(URI.create("http://localhost/api"), MediaTypes.HAL_JSON);

  public void followLink() {
    ParameterizedTypeReference<CollectionModel<Users>> userType = new ParameterizedTypeReference<CollectionModel<Users>>() {
    };
    CollectionModel<Users> usersModel = traverson.follow("users").toObject(userType);  // users 링크를 따라감

    if (usersModel != null) {
      Collection<Users> users = usersModel.getContent();
    }
  }

  // 쓰기 구현 - RestTemplate 함께 사용해야함
  public void postWithRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    String url = traverson.follow("users").asLink().getHref();

    Users postUser = new Users("이순신", 15450428);
    restTemplate.postForObject(url, postUser, Users.class);
  }
}
```