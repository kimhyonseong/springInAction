package com.example.tacocloud.rabbitMQ;

import com.rabbitmq.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitReceiver {
  private static final Logger log = LoggerFactory.getLogger(RabbitReceiver.class);

  @RabbitListener(queues = "rabbit.queue")
  public void receiver(RpcClient.Response message) {
    log.info("Rabbit receiver : {}", message);
  }
}
