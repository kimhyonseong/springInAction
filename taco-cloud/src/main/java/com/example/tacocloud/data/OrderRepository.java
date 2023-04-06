package com.example.tacocloud.data;

import com.example.tacocloud.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
