package com.example.tacocloud.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.username}")
  private String username;

  @Value("${spring.rabbitmq.password}")
  private String password;

  @Value("${spring.rabbitmq.port}")
  private int port;

  @Bean
  Queue queue() {
    return new Queue("rabbit.queue",false);
  }

  @Bean
  DirectExchange directExchange() {
    return new DirectExchange("rabbit.exchange");
  }

  @Bean
  Binding binding(DirectExchange directExchange, Queue queue) {
    return BindingBuilder.bind(queue).to(directExchange).with("rabbit.key");
  }


}
