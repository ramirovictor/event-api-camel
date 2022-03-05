package com.eventapi.eventapi.dto;

import java.util.List;

import com.eventapi.eventapi.dto.imports.EventWrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventJsonImport {
	private String batchId;
	private List<EventWrapper> records;

}
