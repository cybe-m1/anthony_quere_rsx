CREATE TABLE t_friendrequest
(
  id            UUID PRIMARY KEY,
  sender        uuid not null,
  recipient     uuid not null,
  status        varchar             default 'PENDING',
  creation_date time with time zone default now(),
  last_update   time with time zone default now(),
  CONSTRAINT uniq_friendrequest UNIQUE (sender, recipient)
)
