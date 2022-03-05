package com.eventapi.eventapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.service.EventService;

@Component
public class EventProcessor implements Processor{

	
	@Autowired
	private EventService service;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		service.save(exchange.getMessage().getBody(Event.class));
		
	}

}
