package com.ihealthpharm.commons;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseDto<T> {

  private String message;

  @JsonSerialize(using = HttpStatusSerializer.class)
  @JsonDeserialize(using = HttpStatusDeserializer.class)
  private HttpStatus responseStatus = HttpStatus.OK;

  private T result;

  private int status;

  private String responseCode;

  public BaseDto() {
  }

  public BaseDto(String message, HttpStatus responseStatus) {
    this.message = message;
    this.responseStatus = responseStatus;
    this.status = responseStatus.value();
    this.responseCode = responseStatus.getReasonPhrase();
  }

  /**
   * Instantiates a new base dto.
   *
   * @param result         the result
   * @param message        the message
   * @param responseStatus the http response status
   */
  public BaseDto(T result, String message, HttpStatus responseStatus) {
    this.result = result;
    this.message = message;
    this.responseStatus = responseStatus;
    this.status = responseStatus.value();
    this.responseCode = responseStatus.getReasonPhrase();
  }

  public BaseDto(HttpStatus responseStatus, T result) {
    this.result = result;
    this.responseStatus = responseStatus;
    this.status = responseStatus.value();
    this.responseCode = responseStatus.getReasonPhrase();
  }

  public BaseDto(StandardResponse response, HttpStatus responseStatus) {
    this.message = response.toString();
    this.responseStatus = responseStatus;
    this.status = responseStatus.value();
    this.responseCode = responseStatus.getReasonPhrase();
  }

  public ResponseEntity<BaseDto<T>> respond() {
    return new ResponseEntity<>(this, this.getResponseStatus());
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(HttpStatus responseStatus) {
    this.responseStatus = responseStatus;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }
}

