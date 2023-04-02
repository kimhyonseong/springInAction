package com.example.tacocloud.domain;

import jakarta.validation.constraints.NotNull;  // javax가 아닌 이유는 관리하는 회사가 달라졌기 때문
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {
  private Long id;
  private Date createAt;

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;
}
