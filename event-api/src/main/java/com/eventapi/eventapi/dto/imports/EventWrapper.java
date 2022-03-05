package com.eventapi.eventapi.dto.imports;

import java.util.ArrayList;
import java.util.List;

import com.eventapi.eventapi.converter.LocationAttributeConverter;
import com.eventapi.eventapi.dto.EventRequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventWrapper {

	private String transId;

	private String transTms;

	private String rcNum;

	private String clientId;

	private List<EventInternal> event;

	public List<EventRequestDTO> getEvents() {
		
		ArrayList<EventRequestDTO> list = new ArrayList<>(1);
		
		for (EventInternal e : event) {
			EventRequestDTO ev = new EventRequestDTO();
			ev.setTransId(transId);
			ev.setTransTms(transTms);
			ev.setRcNum(rcNum);
			ev.setClientId(clientId);
			ev.setEventCnt(e.getEventCnt());
			ev.setAddrNbr(e.getAddrNbr());
			ev.setLocationCd((new LocationAttributeConverter()).convertToEntityAttribute(e.getLocationCd()));
			ev.setLocationId1(e.getLocationId1());
			ev.setLocationId2(e.getLocationId2());

			list.add(ev);
		}
		return list;
	}
}