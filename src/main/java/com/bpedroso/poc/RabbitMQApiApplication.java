package com.bpedroso.poc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class RabbitMQApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQApiApplication.class, args);
	}
}
