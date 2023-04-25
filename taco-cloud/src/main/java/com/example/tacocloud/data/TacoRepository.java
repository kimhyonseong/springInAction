package com.example.tacocloud.data;

import com.example.tacocloud.domain.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TacoRepository extends JpaRepository<Taco, Long> {
  List<Taco> findAll();
  Page<Taco> findAll(Pageable pageable);
  Optional<Taco> findById(Long id);
}
