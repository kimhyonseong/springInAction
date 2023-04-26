package com.example.tacocloud.domain;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")
@Getter
@Setter
public class TacoModel extends RepresentationModel<TacoModel> {
  private String name;
  private Date createdAt;
  private List<Ingredient> ingredients;

  public TacoModel(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreateAt();
    this.ingredients = taco.getIngredients();
  }
}
