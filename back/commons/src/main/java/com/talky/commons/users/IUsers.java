package com.talky.commons.users;

import java.util.Collection;
import java.util.UUID;

public interface IUsers {

  UserDto getCurentUser();
  UserDto getUserById(UUID id);

  Collection<String> getUserDevices(UUID userId);
}
