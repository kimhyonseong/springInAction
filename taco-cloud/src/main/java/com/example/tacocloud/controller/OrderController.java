package com.example.tacocloud.controller;

import com.example.tacocloud.data.OrderRepository;
import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.Users;
import com.example.tacocloud.property.OrderProps;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
  private OrderProps orderProps;
  private OrderRepository orderRepository;

  public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
    this.orderRepository = orderRepository;
    this.orderProps = orderProps;
  }

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal Users users,
                          @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(users.getFullName());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(users.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(users.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(users.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(users.getZip());
    }
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                             @AuthenticationPrincipal Users users) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

    order.setUsers(users);
    log.info("users : {}",users);
    log.info("order : {}",order);
    orderRepository.save(order);
    sessionStatus.setComplete();
    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal Users users, Model model) {
    model.addAttribute("orders",orderRepository.findByUsersOrderByPlacedAtDesc(users, PageRequest.of(0,orderProps.getPageSize())));
    return "orderList";
  }
}
