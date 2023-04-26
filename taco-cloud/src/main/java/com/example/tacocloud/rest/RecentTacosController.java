package com.example.tacocloud.rest;

import com.example.tacocloud.data.TacoRepository;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoModel;
import com.example.tacocloud.domain.TacoModelAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestController
public class RecentTacosController {
  private TacoModelAssembler tacoModelAssembler;
  private TacoRepository tacoRepository;

  public RecentTacosController(TacoRepository tacoRepository, TacoModelAssembler tacoModelAssembler) {
    this.tacoRepository = tacoRepository;
    this.tacoModelAssembler = tacoModelAssembler;
  }

  @GetMapping(path = "/tacos", produces = MediaTypes.HAL_FORMS_JSON_VALUE)
  public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
    List<Taco> tacos = tacoRepository.findAll();
    return new ResponseEntity<>(tacoModelAssembler.toCollectionModel(tacos), HttpStatus.OK);
  }

  @GetMapping(value = "/tacos/{id}", produces = MediaTypes.HAL_JSON_VALUE)
  public ResponseEntity<TacoModel> getRecentTaco(@PathVariable("id") Long id) {
    return tacoRepository.findById(id).map(tacoModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }
}
