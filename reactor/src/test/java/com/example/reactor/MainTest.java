package com.example.reactor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MainTest {
  static Logger log = LoggerFactory.getLogger(MainTest.class);

  @Test
  void basketTest() {
    final List<String> basket1 = Arrays.asList("kiwi","orange", "lemon", "orange", "lemon", "kiwi");
    final List<String> basket2 = Arrays.asList("banana", "lemon", "lemon", "kiwi");
    final List<String> basket3 = Arrays.asList("strawberry", "orange", "lemon", "grape", "strawberry");
    final List<List<String>> baskets = Arrays.asList(basket1,basket2,basket3);
    final Flux<List<String>> basketFlux = Flux.fromIterable(baskets);
  }

  @Test
  void createFlux_just() {
    //Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
    String[] fruits = new String[]{"Apple", "Orange", "Grape", "Banana", "Strawberry"};
    Flux<String> fruitFlux = Flux.fromArray(fruits);

    fruitFlux.subscribe(f -> System.out.println("Here's some fruit: "+f));

    StepVerifier.create(fruitFlux)
            .expectNext("Apple")
            .expectNext("Orange")
            .expectNext("Grape")
            .expectNext("Banana")
            .expectNext("Strawberry")
            .verifyComplete();
  }

  @Test
  void createFlux_range() {
    Flux<Integer> integerFlux = Flux.range(1,5);

    StepVerifier.create(integerFlux)
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectNext(5)
            .verifyComplete();
  }

  @Test
  void createFlux_interval() {
    Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);

    StepVerifier.create(intervalFlux)
            .expectNext(0L)
            .expectNext(1L)
            .expectNext(2L)
            .expectNext(3L)
            .expectNext(4L)
            .verifyComplete();
  }

  @Test
  void mergeFlux() {
    log.info("before flux");
    Flux<String> characterFlux = Flux.just("Garfield","kojak","Barbossa")
            .delayElements(Duration.ofMillis(500));
    log.info("after characterFlux");
    characterFlux.subscribe(x->log.info("characterFlux - {}",x));

    Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")
            .delaySubscription(Duration.ofMillis(250))
            .delayElements(Duration.ofMillis(500));
    log.info("after foodFlux");
    foodFlux.subscribe(x->log.info("foodFlux - {}",x));

    Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);
    log.info("after mergedFlux");
    mergedFlux.subscribe((x)->log.info("mergedFlux - {}",x));

    StepVerifier.create(mergedFlux)
            .expectNext("Garfield")
            .expectNext("Lasagna")
            .expectNext("kojak")
            .expectNext("Lollipops")
            .expectNext("Barbossa")
            .expectNext("Apples")
            .verifyComplete();
  }
}
