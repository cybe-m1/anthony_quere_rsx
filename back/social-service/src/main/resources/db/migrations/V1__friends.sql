CREATE TABLE t_friendship
(
  id            UUID PRIMARY KEY,
  friend_a      uuid not null,
  friend_b      uuid not null,
  creation_date time with time zone default now(),
  CONSTRAINT uniq_friendship UNIQUE (friend_a, friend_b)
)
