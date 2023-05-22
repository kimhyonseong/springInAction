package com.example.tacocloud.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/rabbit")
public class RabbitProducer {
  private final RabbitTemplate rabbitTemplate;

  public RabbitProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(Message message) {
    rabbitTemplate.convertAndSend("rabbit.exchange", "rabbit.key", message);
  }
}
