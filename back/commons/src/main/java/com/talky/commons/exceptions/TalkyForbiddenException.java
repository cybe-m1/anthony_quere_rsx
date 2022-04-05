package com.talky.commons.exceptions;


import org.springframework.http.HttpStatus;

public class TalkyForbiddenException extends TalkyException {
  public TalkyForbiddenException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }
}
