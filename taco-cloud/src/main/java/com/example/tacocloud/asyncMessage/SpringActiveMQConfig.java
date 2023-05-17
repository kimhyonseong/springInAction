package com.example.tacocloud.asyncMessage;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;


@Configuration
public class SpringActiveMQConfig {
  @Value("${activemq.broker.url}")
  private String brokerUrl;

  @Bean
  public Destination queue() {
    return new ActiveMQQueue("Lee-queue");
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory(brokerUrl);
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory());
    return jmsTemplate;
  }
}
