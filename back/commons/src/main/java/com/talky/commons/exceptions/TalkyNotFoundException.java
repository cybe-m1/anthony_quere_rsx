package com.talky.commons.exceptions;


import org.springframework.http.HttpStatus;

public class TalkyNotFoundException extends TalkyException {
  public TalkyNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
