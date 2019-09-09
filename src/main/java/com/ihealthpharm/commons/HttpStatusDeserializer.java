package com.ihealthpharm.commons;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class HttpStatusDeserializer extends JsonDeserializer<HttpStatus> {
  @Override
  public HttpStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    int status = node.get("code").asInt();
    return HttpStatus.valueOf(status);
  }
}