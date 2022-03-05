package com.eventapi.eventapi.dto;

import javax.persistence.Convert;

import com.eventapi.eventapi.converter.LocationAttributeConverter;
import com.eventapi.eventapi.entity.Event;
import com.eventapi.eventapi.entity.LocationType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Event request DTO")
public class EventRequestDTO {

	//String batchId
	//List records
	
	private Long event_id;

	private String transId;

	private String transTms;

	private String rcNum;

	private String clientId;

	private Integer eventCnt;

	@Convert(converter = LocationAttributeConverter.class)
	private LocationType locationCd;

	private String locationId1;

	private String locationId2;

	private String addrNbr;
	
	public EventRequestDTO(Event event) {
	    this.event_id = event.getEvent_id();
	    this.transId = event.getTransId();
	    this.transTms = event.getTransTms();
	    this.rcNum = event.getRcNum();
	    this.clientId = event.getClientId();
	    this.eventCnt = event.getEventCnt();
	    this.locationCd = event.getLocationCd();
	    this.locationId1 = event.getLocationId1();
	    this.locationId2 = event.getLocationId2();
	    this.addrNbr = event.getAddrNbr();
	}
	
	
	
	public  Event convertToEventEntity () {
		return new Event(event_id, transId, transTms, rcNum, clientId, eventCnt, locationCd, locationId1, locationId2, addrNbr);
	}
	public  Event convertToEventEntity (Long id) {
		return new Event(id, transId, transTms, rcNum, clientId, eventCnt, locationCd, locationId1, locationId2, addrNbr);
	}
	
}
