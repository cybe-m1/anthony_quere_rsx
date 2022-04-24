CREATE TABLE IF NOT EXISTS t_message(
  id UUID PRIMARY KEY,
  friendship_id UUID NOT NULL references t_friendship(id),
  author UUID NOT NULL,
  content varchar,

  created_at timestamp with time zone not null default now()
);
