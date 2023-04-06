package com.example.tacocloud.web;

import com.example.tacocloud.data.IngredientRepository;
import com.example.tacocloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepository;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public Ingredient convert(String id) {
    Optional<Ingredient> optionalIngredient = ingredientRepository.findById(Long.valueOf(id));
    return optionalIngredient.orElse(null);
  }
}
