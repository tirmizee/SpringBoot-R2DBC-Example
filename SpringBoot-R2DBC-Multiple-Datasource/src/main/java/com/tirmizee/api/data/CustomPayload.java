package com.tirmizee.api.data;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(using = CustomPayload.CustomPayloadSerializer.class)
public class CustomPayload implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String displayName;
	private String displayValue;
	
	public static class CustomPayloadSerializer extends JsonSerializer<CustomPayload> {

		@Override
		public void serialize(CustomPayload value, JsonGenerator generator, SerializerProvider serializers)
				throws IOException {
			generator.writeStartObject();
			generator.writeStringField("name", value.getName());
			generator.writeStringField(value.getDisplayName(), value.getDisplayValue());
			generator.writeEndObject();
		}
		
	}

}
