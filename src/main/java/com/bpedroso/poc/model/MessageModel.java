package com.bpedroso.poc.model;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uuid;
	private String code;
	private String timestamp;
	private List<Customer> customer;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "MessageModel [" + (uuid != null ? "uuid=" + uuid + ", " : "")
				+ (code != null ? "code=" + code + ", " : "")
				+ (timestamp != null ? "timestamp=" + timestamp + ", " : "")
				+ (customer != null ? "customer=" + customer : "") + "]";
	}

}
