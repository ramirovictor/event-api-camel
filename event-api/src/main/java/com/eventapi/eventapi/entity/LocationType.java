package com.eventapi.eventapi.entity;

public enum LocationType {

	CUSTOMER_NUMBER("CUSTOMER NUMBER"),
	OUTLET_ID("OUTLET ID"),
	DESTINATION("DESTINATION");

	private final String location;

	private LocationType(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

}
