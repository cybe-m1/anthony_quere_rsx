package com.talky.commons.exceptions;


import org.springframework.http.HttpStatus;

public class TalkyBadRequestException extends TalkyException {
  public TalkyBadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}
