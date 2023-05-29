CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA public;

CREATE TABLE account
(
    id             UUID PRIMARY KEY      DEFAULT public.uuid_generate_v4(),
    email          VARCHAR(255) NOT NULL UNIQUE,
    user_name      VARCHAR(30)  NOT NULL,
    is_active      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at     TIMESTAMPTZ  NOT NULL DEFAULT now(),
    last_access_at TIMESTAMPTZ  NOT NULL DEFAULT now()
);