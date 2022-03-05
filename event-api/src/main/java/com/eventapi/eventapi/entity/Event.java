package com.eventapi.eventapi.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eventapi.eventapi.converter.LocationAttributeConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Table(name= "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long event_id;
	
	@Column(name= "trans_id")
	private String transId;
	
	@Column(name= "trans_tms")
	private String transTms;
	
	@Column(name= "rc_num")
	private String rcNum;
	
	@Column(name= "client_id")
	private String clientId;
	
	@Column(name= "event_cnt")
	private Integer eventCnt;
	
//	@Column(name= "location_cd")
//	private String locationCd;
	@Convert(converter = LocationAttributeConverter.class)
	@Column(name= "location_cd")
	private LocationType locationCd;
	
	@Column(name= "location_id1")
	private String locationId1;
	
	@Column(name= "location_id2")
	private String locationId2;
	
	@Column(name= "addr_nbr")
	private String addrNbr;
//
//	public Event(Long event_id, String transId, String transTms, String rcNum, String clientId, Integer eventCnt,
//			LocationType locationCd, String locationId1, String locationId2, String addrNbr) {
//		super();
//		this.event_id = event_id;
//		this.transId = transId;
//		this.transTms = transTms;
//		this.rcNum = rcNum;
//		this.clientId = clientId;
//		this.eventCnt = eventCnt;
//		this.locationCd = locationCd;
//		this.locationId1 = locationId1;
//		this.locationId2 = locationId2;
//		this.addrNbr = addrNbr;
//	}
	
	
	

}
