package com.eventapi.eventapi.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.eventapi.eventapi.entity.LocationType;

@Converter
public class LocationAttributeConverter implements AttributeConverter<LocationType, String>{

	@Override
	public String convertToDatabaseColumn(LocationType attribute) {
		
		return attribute != null ? attribute.getLocation() : null;
	}

	@Override
	public LocationType convertToEntityAttribute(String dbData) {
		
		return (dbData == null) ? null : Stream.of(LocationType.values()).filter(type -> type.getLocation().equals(dbData)).findAny()
				.orElseThrow(() -> new IllegalArgumentException(
						"Code " + dbData + " is not recognized as a valid location type"));
	}

}
