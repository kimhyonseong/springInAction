package com.example.tacocloud.controller;

import com.example.tacocloud.data.IngredientRepository;
import com.example.tacocloud.data.TacoRepository;
import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Ingredient.Type;
import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.Taco;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
  private final IngredientRepository ingredientRepository;
  private TacoRepository tacoRepository;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepository,
                              TacoRepository tacoRepository) {
    this.ingredientRepository = ingredientRepository;
    this.tacoRepository = tacoRepository;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(ingredients::add);

    Type[] types = Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
    }
    model.addAttribute("taco",new Taco());

    return "design";
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @PostMapping
  public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      log.error(errors.toString());

      return "design";
    }

    Taco saveTaco = tacoRepository.save(taco);
    order.addDesign(saveTaco);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
            .filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }
}
