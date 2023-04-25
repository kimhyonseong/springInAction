package com.example.tacocloud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;  // javax가 아닌 이유는 관리하는 회사가 달라졌기 때문
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date createAt;

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @Size(min = 1, message = "You must choose at least 1 ingredient")
  @ManyToMany(targetEntity = Ingredient.class)
  private List<Ingredient> ingredients;

  @PrePersist
  void createdAt() {
    this.createAt = new Date();
  }
}
