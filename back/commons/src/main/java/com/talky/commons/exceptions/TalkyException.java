package com.talky.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public abstract class TalkyException extends RuntimeException {
  private HttpStatus status;
  private String message;
}
