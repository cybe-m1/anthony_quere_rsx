CREATE TABLE IF NOT EXISTS t_user (
    device_id varchar PRIMARY KEY,
    user_id uuid references t_user(user_id)
)


