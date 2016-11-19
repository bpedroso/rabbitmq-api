package com.bpedroso.poc.controller;

import static java.lang.Integer.valueOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpedroso.poc.model.MessageModel;
import com.bpedroso.poc.service.MessageService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api")
public class MessageController {

	@Autowired
	private MessageService customerService;

	@ApiImplicitParams({
        @ApiImplicitParam(name = "messages", value = "Messages of customers to generate into the rabbitmq", required = true, dataType = "int", paramType = "query", defaultValue="1"),
        @ApiImplicitParam(name = "customers", value = "Customers inside message", required = true, dataType = "int", paramType = "query", defaultValue="1")
      })
	@ApiResponses(value = { 
    		@ApiResponse(code = 200, message = "Success", response = String.class),
    		@ApiResponse(code = 500, message = "Failure", response = String.class)
	  })
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> addCustomers(
			@RequestParam(value = "messages", defaultValue = "1") String messages,
			@RequestParam(value = "customers", defaultValue = "1") String customers) {
		ResponseEntity<String> responseEntity;
		try {
			final List<MessageModel> messagesModel = customerService.produce(valueOf(messages), valueOf(customers));
			responseEntity = new ResponseEntity<String>(new Gson().toJson(messagesModel), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("{" + e.getMessage() + "}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
}
