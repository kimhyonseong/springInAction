package com.example.tacocloud.domain;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {
  @Getter
  private final String name;

  @Getter
  private final Date createdAt;

  @Getter
  private final List<Ingredient> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreateAt();
    this.ingredients = taco.getIngredients();
  }
}
