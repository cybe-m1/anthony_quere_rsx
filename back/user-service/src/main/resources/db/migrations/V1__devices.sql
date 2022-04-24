CREATE TABLE IF NOT EXISTS t_device (
    device_id varchar PRIMARY KEY,
    user_id uuid references t_user(id)
)


