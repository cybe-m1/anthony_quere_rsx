package com.talky.commons.social;

import java.util.UUID;

public interface ISocial {

  FriendshipDto findFriendshipByFriend(UUID friend);
}
