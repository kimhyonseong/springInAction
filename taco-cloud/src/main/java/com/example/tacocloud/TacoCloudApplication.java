package com.example.tacocloud;

import com.example.tacocloud.data.IngredientRepository;
import com.example.tacocloud.data.TacoRepository;
import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Date;

@SpringBootApplication
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
  }

  @Bean
  @Profile("!prod")
  public CommandLineRunner dataLoader(IngredientRepository repository, TacoRepository tacoRepository) {
    return args -> {
      repository.save(new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP));
      repository.save(new Ingredient("COTO","Corn Tortilla",Ingredient.Type.WRAP));
      repository.save(new Ingredient("GRBF","Ground Beef",Ingredient.Type.PROTEIN));
      repository.save(new Ingredient("CARN","Carnitas",Ingredient.Type.PROTEIN));
      repository.save(new Ingredient("TMTO","Diced Tomatoes",Ingredient.Type.VEGGIES));
      repository.save(new Ingredient("LETC","Lettuce",Ingredient.Type.VEGGIES));
      repository.save(new Ingredient("CHED","Cheddar",Ingredient.Type.CHEESE));
      repository.save(new Ingredient("JACK","Monterrey Jack",Ingredient.Type.CHEESE));
      repository.save(new Ingredient("SLSA","Salsa",Ingredient.Type.SAUCE));
      repository.save(new Ingredient("SRCR","Sour Cream",Ingredient.Type.SAUCE));

      tacoRepository.save(new Taco("First Taco", new Date()));
      tacoRepository.save(new Taco("Second Taco", new Date()));
      tacoRepository.save(new Taco("Third Taco", new Date()));
    };
  }
}
