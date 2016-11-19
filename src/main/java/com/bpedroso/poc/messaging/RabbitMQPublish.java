package com.bpedroso.poc.messaging;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublish {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPublish.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(final String exchange, final String routingKey, final String payLoad) {
		final String messageId = UUID.randomUUID().toString();
		final CorrelationData correlationData = new CorrelationData(messageId);
		this.rabbitTemplate.convertAndSend(exchange, routingKey, payLoad, correlationData);
		logSuccess(exchange, payLoad, messageId);
	}

	@Async
	private void logSuccess(final String messageId, final Object data, final String exchange) {
		LOGGER.info("Sent Message CorrelationId -> {}", messageId);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Queuing {} to {}", data, exchange);
		}
	}
}
