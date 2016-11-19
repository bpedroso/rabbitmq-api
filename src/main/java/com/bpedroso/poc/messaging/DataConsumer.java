package com.bpedroso.poc.messaging;

import static com.bpedroso.poc.configuration.RabbitMQConstants.QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.poc.model.MessageModel;
import com.bpedroso.poc.service.MessageService;
import com.google.gson.Gson;

@Component
public class DataConsumer {

	@Autowired
	private MessageService service;

	private final static Logger LOGGER = LoggerFactory.getLogger(DataConsumer.class);

	@RabbitListener(queues = QUEUE)
    public void listenMessage(final Message message) throws Exception {

		try {
			final MessageProperties messageProperties = message.getMessageProperties();
			
			final String messageBody = new String(message.getBody());
			final MessageModel model = new Gson().fromJson(messageBody, MessageModel.class);
			LOGGER.info("Receving id {} message {}.", messageProperties.getCorrelationIdString(), messageBody);

        	this.service.processRead(model, messageProperties.getMessageId());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(e);
		}
    }

}
