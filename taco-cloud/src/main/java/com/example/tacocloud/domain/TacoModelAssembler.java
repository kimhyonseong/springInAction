package com.example.tacocloud.domain;

import com.example.tacocloud.rest.RecentTacosController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {
  public TacoModelAssembler() {
    super(RecentTacosController.class, TacoModel.class);
  }

  public TacoModelAssembler(Class<?> controllerClass, Class<TacoModel> resourceType) {
    super(controllerClass, resourceType);
  }

  @Override
  public TacoModel toModel(Taco entity) {
    TacoModel tacoModel = new TacoModel(entity);
    tacoModel.add(linkTo(methodOn(RecentTacosController.class).getRecentTaco(entity.getId())).withSelfRel());

    return tacoModel;
  }

  @Override
  public CollectionModel<TacoModel> toCollectionModel(Iterable<? extends Taco> entities) {
    CollectionModel<TacoModel> tacoModels = super.toCollectionModel(entities);
    tacoModels.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withSelfRel());

    return tacoModels;
  }
}