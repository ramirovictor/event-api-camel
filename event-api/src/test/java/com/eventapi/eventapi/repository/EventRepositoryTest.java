package com.eventapi.eventapi.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.entity.LocationType;

@DataJpaTest
public class EventRepositoryTest {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void shouldReturnTrueWhensaveEventTest() {
		
		Event event = Event.builder()
				.transId("0000abf8-d1f5")
				.transTms("20151022")
				.rcNum("10002")
				.clientId("RPS-00001")
				.eventCnt(1)
				.locationCd(LocationType.DESTINATION)
				.locationId1("T8C")
				.locationId2("1J7")
				.addrNbr("0000000001")
				.build();
		
		eventRepository.save(event);
		Assertions.assertThat(event.getEvent_id()).isGreaterThan(0);
	}
	
	@Test
	public void shouldReturnTrueWhengetEventTest() {
		
		Event event = eventRepository.findById(1L).get();
		
		Assertions.assertThat(event.getEvent_id()).isEqualTo(1L);
	}
	
	@Test
	public void getListOfEventsTest() {
		
		List<Event> events= eventRepository.findAll();
		
		Assertions.assertThat(events.size()).isGreaterThan(0);
	}
	
	@Test
	public void shouldReturnTrueWhenupdateEventTest() {
		
		Event event = eventRepository.findById(1L).get();
		event.setRcNum("10002");
		Event eventUpdate = eventRepository.save(event);
		
		Assertions.assertThat(eventUpdate.getRcNum()).isEqualTo("10002");
	}
	
	@Test
	public void shouldReturnTrueWhendeleteEventTest() {
		
		Event event = eventRepository.findById(1L).get();
		
		eventRepository.delete(event);
		
		Event event1 = null;
		
		Optional<Event> optionalEvent = eventRepository.findById(1L);
		
		if(optionalEvent.isPresent()) {
			event1 = optionalEvent.get();
		}
		
		Assertions.assertThat(event1).isNull();
	}

}
