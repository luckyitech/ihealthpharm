package com.ihealthpharm.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class HttpStatusSerializer extends JsonSerializer<HttpStatus> {
  @Override
  public void serialize(HttpStatus httpStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeNumberField("code", httpStatus.value());
    jsonGenerator.writeStringField("reasonPhrase", httpStatus.getReasonPhrase());
    jsonGenerator.writeEndObject();
  }
}

