package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class MainTest {
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
    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
    System.out.println(fruitFlux);
    fruitFlux.subscribe(f -> System.out.println("Here's some fruit: "+f));
  }
}
