package com.talky.commons.auth;

import java.util.Optional;

public interface IAuthentication {
  Optional<String> getCurrentUserId();
}
