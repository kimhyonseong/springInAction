package com.example.tacocloud.domain;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {
  public TacoResourceAssembler(Class<?> controllerClass, Class<TacoResource> resourceType) {
    super(controllerClass, resourceType);
  }

  @Override
  public TacoResource toModel(Taco entity) {
    return new TacoResource(entity);
  }
}
