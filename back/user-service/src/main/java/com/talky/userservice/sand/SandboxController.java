package com.talky.userservice.sand;

import com.talky.commons.exceptions.TalkyBadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SandboxController {

  @GetMapping("sand")
  public void makeError() {
    throw new TalkyBadRequestException("Bad request :(");
  }
}
