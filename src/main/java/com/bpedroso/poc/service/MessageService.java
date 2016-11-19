package com.bpedroso.poc.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpedroso.poc.messaging.DataProducer;
import com.bpedroso.poc.model.Customer;
import com.bpedroso.poc.model.MessageModel;

@Service
public class MessageService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DataProducer publisher;

	public void processRead(final MessageModel customer, final String messageId) throws Exception {
		Thread.sleep(3000);
	}
	
	public List<MessageModel> produce(final int count, final int customersCount) throws Exception {

		final List<MessageModel> customer = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			MessageModel model = new MessageModel();
			model.setUuid(UUID.randomUUID().toString());
			model.setCode(Integer.toString(i));
			model.setTimestamp(Long.toString(Calendar.getInstance().getTimeInMillis()));

			List<Customer> customers = new ArrayList<>();
			for (int j = 0; j < customersCount; j++) {
				customers.add(new Customer("name"+j, Integer.toString(RandomUtils.nextInt(900000000, 999999999))));
			}

			model.setCustomer(customers);
			customer.add(model);
		}

		this.publisher.send(customer);
		
		return customer;
	}

}
