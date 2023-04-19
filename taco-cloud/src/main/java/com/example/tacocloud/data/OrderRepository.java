package com.example.tacocloud.data;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {
  List<Order> findByDeliveryZip(String deliveryZip);
  List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
  List<Order> findByUsersOrderByPlacedAtDesc(Users users, Pageable pageable);
}