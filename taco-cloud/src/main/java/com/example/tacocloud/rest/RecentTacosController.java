package com.example.tacocloud.rest;

import com.example.tacocloud.data.TacoRepository;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoResource;
import com.example.tacocloud.domain.TacoResourceAssembler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RepositoryRestController
public class RecentTacosController {
  private TacoRepository tacoRepository;

  public RecentTacosController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  @GetMapping(path = "/tacos", produces = "application/hal+json")
  public ResponseEntity<CollectionModel<TacoResource>> recentTacos() {
    PageRequest pageRequest = PageRequest.of(0,12, Sort.by("createdAt").descending());
//    List<Taco> tacos = tacoRepository.findAll(pageRequest);
//    List<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacos);
//    CollectionModel<TacoResource> recentResources = new CollectionModel<>(tacoResources);
    //return new ResponseEntity<>();
    return null;
  }
}
