package com.example.tacocloud.data;

import com.example.tacocloud.domain.Taco;

public interface TacoRepository {
  Taco save(Taco taco);
}
