create table if not exists posts
(
  id        uuid primary key,
  author    uuid                     not null,
  created_at timestamp with time zone not null default now(),
  content   varchar                  not null,
  privacy   varchar                  not null default 'PUBLIC'
);

create table if not exists post_assets
(
  asset_id uuid not null primary key,
  post_id  uuid not null
);
