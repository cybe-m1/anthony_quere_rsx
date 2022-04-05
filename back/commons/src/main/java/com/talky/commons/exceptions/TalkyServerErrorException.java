package com.talky.commons.exceptions;


import org.springframework.http.HttpStatus;

public class TalkyServerErrorException extends TalkyException {
  public TalkyServerErrorException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }
}
