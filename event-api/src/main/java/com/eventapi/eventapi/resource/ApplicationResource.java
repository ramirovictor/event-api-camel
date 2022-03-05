package com.eventapi.eventapi.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.eventapi.eventapi.controller.EventProcessor;
import com.eventapi.eventapi.dto.EventJsonImport;
import com.eventapi.eventapi.dto.imports.EventWrapper;
import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.service.EventService;

@Component
public class ApplicationResource extends RouteBuilder {
	
	@Autowired
	private EventService service;

	@BeanInject
	private EventProcessor processor;

	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet").port(8080).host("localhost").bindingMode(RestBindingMode.json);

		rest().get("hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.setBody(constant("Welcome Apache camel")).endRest();

		rest().get("/getevents").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(() -> service.listAll())
				.endRest();

		rest().post("/addEvents").consumes(MediaType.APPLICATION_JSON_VALUE).type(Event.class).outType(Event.class)
				.route().process(processor)
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
				.endRest();

		rest().delete("/deleteevent/{id}").route().bean(service, "delete(${header.id})").endRest();

		rest().post("/addjsonexternal").consumes(MediaType.APPLICATION_JSON_VALUE).type(EventJsonImport.class)
				.outType(EventJsonImport.class).route().process(exchange -> {
					EventJsonImport book = exchange.getIn().getBody(EventJsonImport.class);
					for (EventWrapper event : book.getRecords()) {
						event.getEvents().forEach(record -> {
							service.save(record.convertToEventEntity());
						});
					}
				}).endRest();

		rest().put("updateevent/{id}").produces(MediaType.APPLICATION_JSON_VALUE).type(Event.class).route().choice()
				.when().simple("${header.id} < 1").bean(ApplicationResource.class).otherwise().process(exchange -> {
					service.updateEvent(Long.valueOf(exchange.getIn().getHeader("id").toString()),
							exchange.getMessage().getBody(Event.class));

				}).endRest();

	}
	
//	ThreadPoolProfile profile = camelContext.getExecutorServiceManager().getDefaultThreadPoolProfile();
//	profile.setPoolSize(5);
//	profile.setMaxPoolSize(10);
	
//	.parallelProcessing()

}
