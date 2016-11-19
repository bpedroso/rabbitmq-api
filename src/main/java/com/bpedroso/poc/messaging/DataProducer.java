package com.bpedroso.poc.messaging;

import static com.bpedroso.poc.configuration.RabbitMQConstants.EXCHANGE;
import static com.bpedroso.poc.configuration.RabbitMQConstants.QUEUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.poc.model.MessageModel;
import com.google.gson.Gson;

@Component
public class DataProducer {

	@Autowired
	private RabbitMQPublish publisher;

	public void send(final List<MessageModel> customer) throws Exception {
		customer.stream().forEach(payLoad -> this.publisher.send(EXCHANGE, QUEUE, new Gson().toJson(payLoad)));
	}

}
