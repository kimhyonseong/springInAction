# RestTemplate
- JdbcTemplate와 같은 Template이며 RESTful 호출과 응답에 관련된 메소드를 제공
- RestTemplate은 RESTful 웹 서비스를 호출하고 결과를 받아오기 위한 클라이언트 측 HTTP 통신 라이브러리
- 서버와 클라이언트 간의 REST API를 쉽게 호출, 응답 처리
- 현재는 __WebClient__ 라는 새로운 클라이언트 등장

---
### RestTemplate 이전 통신 방법
#### 1. URLConnection
```java
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Url() {
  public void urlConnection() throws Exception {
    URL url = new URL("http://www.google.com");
    URLConnection conn = url.openConnection();
    InputStream inputStream = conn.getInputStream();
    byte[] data = new byte[1024];
    inputStream.read(data);
  }
}
```
#### 2. HttpClient - httpcomponents dependency 필요

```java
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Url() {
  public void httpClientGet() throws Exception {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet("http://www.google.com");
    httpGet.addHeader("User-Agent", "Mozila/5.0");
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

    InputStream inputStream = httpResponse.getEntity().getContent();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(inputStreamReader);
  }
}
```
---
### RestTemplate 예제
- RestTemplate는 HttpClient를 추상화하여 제공

```java
import org.springframework.web.client.RestTemplate;

public class Rest() {
  RestTemplate rest = new RestTemplate();
  User user = new User(1L, "이순신", 15450428);
  
  public void get() {
    User getUser = rest.getForEntity("http://localhost/users/{id}",User.class,user.getId());
    System.out.println(user);
  }
  
  public void put() {
    rest.put("http://localhost/users/{id}",user,user.getId());
  }
  
  public void delete() {
    rest.delte("http://localhost/users/{id}",user.getId());
  }
  
  public void post() {
    User postUser = new User(2L, "김유신", 05950818);
    User resultUser = rest.postForEntity("http://localhost/users",postUser,User.class);
    System.out.println(resultUser);
  }
}
```