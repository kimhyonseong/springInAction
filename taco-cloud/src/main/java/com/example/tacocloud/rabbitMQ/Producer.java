package com.example.tacocloud.rabbitMQ;

import com.example.tacocloud.domain.TestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Destination;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class Producer {
  private final RabbitTemplate rabbitTemplate;
  private final Destination queue;

  public Producer(RabbitTemplate rabbitTemplate, Destination queue) {
    this.rabbitTemplate = rabbitTemplate;
    this.queue = queue;
  }

  @PostMapping("/message")
  public TestObject sendMessage(@RequestBody TestObject testObject) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String orderJson = objectMapper.writeValueAsString(testObject);

      jmsTemplate.convertAndSend("Lee-queue",orderJson);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return testObject;
  }
}
