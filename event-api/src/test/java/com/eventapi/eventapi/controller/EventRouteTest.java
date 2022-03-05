package com.eventapi.eventapi.controller;

import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.resource.ApplicationResource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class EventRouteTest extends CamelTestSupport {
	
	@Autowired
	private CamelContext camelContext;
   
	@Override
    public RoutesBuilder createRouteBuilder() throws Exception {
        return new ApplicationResource();
    }
	
    @Test
    public void checkFileExistsInOutputDirectory() throws InterruptedException
    {
    	Exchange exchange = new DefaultExchange(camelContext);
    	Message in = new DefaultMessage(camelContext);
    	in.setHeader("event_id", 1);
    	exchange.setIn(in);
    	exchange = camelContext.createProducerTemplate().send("direct:event", exchange);
    	Message message = exchange.getIn();
    	assertEquals("1", ((Event) message.getBody()).getEvent_id());

    }

	

}
