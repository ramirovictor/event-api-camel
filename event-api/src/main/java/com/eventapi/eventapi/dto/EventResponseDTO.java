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
@Schema(name = "Event response DTO")
public class EventResponseDTO {

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
	
	public static EventResponseDTO convertToEventDTO (Event event) {
		return new EventResponseDTO(
				event.getEvent_id(),
				event.getTransId(),
				event.getTransTms(),
				event.getRcNum(),
				event.getClientId(),
				event.getEventCnt(),
				event.getLocationCd(),
				event.getLocationId1(),
				event.getLocationId2(),
				event.getAddrNbr()
				);
	}

}
