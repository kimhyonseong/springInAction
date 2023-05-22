package com.example.tacocloud.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RabbitController {
  private final RabbitProducer rabbitProducer;

  public RabbitController(RabbitProducer rabbitProducer) {
    this.rabbitProducer = rabbitProducer;
  }

  @PostMapping("RabbitSend")
  public String send(Message message) {
    rabbitProducer.sendMessage(message);
    return "";
  }
}
