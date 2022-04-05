package com.talky.commons.users;

import java.util.UUID;

public interface IUsers {

  UserDto getCurentUser();
  UserDto getUserById(UUID id);
}
