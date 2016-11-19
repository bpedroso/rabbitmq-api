package com.bpedroso.poc.configuration;

public abstract class RabbitMQConstants {
	
	public static final boolean DURABLE = true;
	public static final boolean AUTO_DELETE = false;
	
	public final static String QUEUE = "bruno.q";
	public final static String DLQ = "bruno.dlq";
	public final static String EXCHANGE = "bruno.e";
	public final static String DLX = "bruno.dlx";
}
