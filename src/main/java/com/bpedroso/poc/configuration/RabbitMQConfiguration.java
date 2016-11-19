package com.bpedroso.poc.configuration;

import static com.bpedroso.poc.configuration.RabbitMQConstants.AUTO_DELETE;
import static com.bpedroso.poc.configuration.RabbitMQConstants.DURABLE;
import static com.bpedroso.poc.configuration.RabbitMQConstants.QUEUE;

import java.io.Serializable;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Bean
	public Queue brunoQueue() {
		return new Queue(QUEUE, DURABLE);
	}

	@Bean
	public Queue brunoDlq() {
		return new Queue(RabbitMQConstants.DLQ, DURABLE);
	}

	@Bean
	public DirectExchange brunoExchange() {
		return new DirectExchange(RabbitMQConstants.EXCHANGE, DURABLE, AUTO_DELETE);
	}

	@Bean
	FanoutExchange brunoDlx() {
		return new FanoutExchange(RabbitMQConstants.DLX);
	}

	/*
	 * Bindings
	 */
	@Bean
	Binding bindingBrunoQueue(final Queue brunoQueue, final DirectExchange brunoExchange) {
		return BindingBuilder.bind(brunoQueue).to(brunoExchange).with(QUEUE);
	}

	@Bean
	Binding bindingBrunoDlq(final Queue brunoDlq, final FanoutExchange brunoDlx) {
		return BindingBuilder.bind(brunoDlq).to(brunoDlx);
	}
}
