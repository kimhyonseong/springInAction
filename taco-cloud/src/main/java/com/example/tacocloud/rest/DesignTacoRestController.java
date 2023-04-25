package com.example.tacocloud.rest;

import com.example.tacocloud.data.TacoRepository;
import com.example.tacocloud.domain.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {
  private TacoRepository tacoRepository;

  public DesignTacoRestController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  @GetMapping("/recent")
  public Page<Taco> recentTacos() {
    PageRequest pageRequest = PageRequest.of(0,12, Sort.by("createAt").descending());
    return tacoRepository.findAll(pageRequest);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optionalTaco = tacoRepository.findById(id);

    return optionalTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}
