CREATE TABLE IF NOT EXISTS t_user (
  id UUID PRIMARY KEY,
  account_id VARCHAR,
  displayed_name VARCHAR,
  profile_picture VARCHAR,
  default_profile_picture VARCHAR,
  last_seen timestamp with time zone DEFAULT now(),
  CONSTRAINT uniq_account_id UNIQUE (account_id)
)


