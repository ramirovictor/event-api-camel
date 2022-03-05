package com.eventapi.eventapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eventapi.eventapi.dto.EventJsonImport;
import com.eventapi.eventapi.dto.EventRequestDTO;
import com.eventapi.eventapi.dto.EventResponseDTO;
import com.eventapi.eventapi.dto.imports.EventWrapper;
import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**@Tag(name = "Event Processor")
@RestController
@RequestMapping("/event")**/
public class EventController {
/**
	@Autowired
	private EventService eventService;

	@Operation(summary = "Get all events")
	@GetMapping
	public List<EventResponseDTO> listAllEvents() {
		return eventService.listAll().stream().map(event -> EventResponseDTO.convertToEventDTO(event))
				.collect(Collectors.toList());
	}

	@Operation(summary = "Get a event by its id")
	@GetMapping("/{id}")
	public ResponseEntity<EventResponseDTO> findEventById(
			@Parameter(description = "id of event to be searched") @PathVariable Long id) {

		Optional<Event> event = eventService.findById(id);
		return event.isPresent() ? ResponseEntity.ok(EventResponseDTO.convertToEventDTO(event.get()))
				: ResponseEntity.notFound().build();
	}

	@Operation(summary = "Save a event")
	@PostMapping
	public ResponseEntity<EventResponseDTO> saveEvent(@RequestBody EventRequestDTO eventDTO) {

		Event eventSave = eventService.save(eventDTO.convertToEventEntity());

		return ResponseEntity.status(HttpStatus.CREATED).body(EventResponseDTO.convertToEventDTO(eventSave));
	}

	@Operation(summary = "Update a event by its id")
	@PutMapping("/{id}")
	public ResponseEntity<Event> updateCategory(@PathVariable Long id, @RequestBody EventRequestDTO eventDTO) {
		return ResponseEntity.ok(eventService.updateEvent(id, eventDTO.convertToEventEntity(id)));
	}

	@Operation(summary = "Delete a event by its id")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		eventService.delete(id);
	}

	@Operation(summary = "Accepts a json payload of an external API and save")
	@PostMapping(value = "/imports", produces = "application/json")
	public ResponseEntity<EventResponseDTO> saveEventJsonExternalAPI(@RequestBody EventJsonImport importData) {

		System.out.println(importData.getBatchId());
		
		for (EventWrapper event : importData.getRecords()) {
			event.getEvents().forEach(record -> {
				eventService.save(record.convertToEventEntity());
			});
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
//	@Operation(summary = "Accepts a json payload with RequestPart and save")
//	@RequestMapping(path = "/requestpart/employee", method =RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
//	public ResponseEntity<EventResponseDTO> saveEventJsonRequestPart(@RequestPart EventJsonImport importData, MultipartFile document) {
//		
//		importData.setRecords(document);
//		System.out.println(importData.getBatchId());
//		
//		for (EventWrapper event : importData.getRecords()) {
//			event.getEvents().forEach(record -> {
//				eventService.save(record.convertToEventEntity());
//			});
//		}
//		
//		return ResponseEntity.status(HttpStatus.CREATED).build();
//	}
	**/
}
