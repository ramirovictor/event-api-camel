package com.eventapi.eventapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> listAll() {

		return eventRepository.findAll();
	}

	public Optional<Event> findById(Long id) {
		return eventRepository.findById(id);
	}
	
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public Event updateEvent(Long id, Event event) {
		Event eventSave = validateEventExist(id);
		
		BeanUtils.copyProperties(event, eventSave, "id");
		
		return eventRepository.save(eventSave);
	}
	
	public void delete(Long id) {
		eventRepository.deleteById(id);
	}
	

	private Event validateEventExist(Long id) {

		Optional<Event> event = findById(id);
		if(event.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return event.get();
		
	}

}
