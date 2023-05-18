package com.example.tacocloud.asyncMessage;

import com.example.tacocloud.domain.TestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Destination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class Producer {
  private final JmsTemplate jmsTemplate;
  private final Destination queue;

  public Producer(JmsTemplate jmsTemplate, Destination queue) {
    this.jmsTemplate = jmsTemplate;
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
