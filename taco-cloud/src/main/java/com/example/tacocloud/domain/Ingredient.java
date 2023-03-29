package com.example.tacocloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor  //final 또는 @NotNull 변수에 대한 생성자 생성
@AllArgsConstructor  //전체를 매개변수로한 생성자 생성
public class Ingredient {
  private Long id;
  private final String code;
  private final String name;
  private final Type type;

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}
