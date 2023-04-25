package com.example.tacocloud.data;

import com.example.tacocloud.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TacoRepository extends CrudRepository<Taco, Long> {
  List<Taco> findAll();
  List<Taco> findAll(PageRequest pageRequest);
  Optional<Taco> findById(Long id);
}
