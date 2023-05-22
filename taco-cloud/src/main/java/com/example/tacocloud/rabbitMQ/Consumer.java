package com.example.tacocloud.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
  private static final Logger log = LoggerFactory.getLogger(Consumer.class);

  @JmsListener(destination = "Lee-queue")
  public void consumeMessage(String message) {
    log.info("Message received from activemq -- {}",message);
  }
}
