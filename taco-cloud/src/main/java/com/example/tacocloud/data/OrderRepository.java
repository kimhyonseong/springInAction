package com.example.tacocloud.data;

import com.example.tacocloud.domain.Order;

public interface OrderRepository {
  Order save(Order order);
}
