CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS documents;
-- DROP TABLE IF EXISTS tags;

CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    image_url VARCHAR(255)
);

CREATE TABLE documents(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    user_id UUID
);

CREATE TABLE tags(
    id serial PRIMARY KEY,
    name VARCHAR(255),
    document_id UUID,
    user_id UUID
)

