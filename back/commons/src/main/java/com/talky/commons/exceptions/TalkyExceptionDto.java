package com.talky.commons.exceptions;

import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class TalkyExceptionDto {
  private String status;
  private String message;

  public TalkyExceptionDto(TalkyException ex) {
    this.status = ex.getStatus().getReasonPhrase();
    this.message = ex.getMessage();
  }
}
