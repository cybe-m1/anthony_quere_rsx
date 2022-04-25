package com.talky.commons.exceptions;


import org.springframework.http.HttpStatus;

public class TalkyUnauthorizedException extends TalkyException {
  public TalkyUnauthorizedException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
