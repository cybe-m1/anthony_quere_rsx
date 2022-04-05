package com.talky.commons.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {


  @ExceptionHandler(value = TalkyException.class)
  public ResponseEntity<TalkyExceptionDto> managePrepaymentAlreadyExistsResponseException(
    TalkyException ex) {
    TalkyExceptionDto error = new TalkyExceptionDto(ex);
    return new ResponseEntity<>(error, ex.getStatus());
  }

}
