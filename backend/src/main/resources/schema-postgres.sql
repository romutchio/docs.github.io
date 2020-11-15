CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS tags;

CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    image_url VARCHAR(255)
);

CREATE TABLE documents(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    user_id UUID,
    data BYTEA
);

CREATE TABLE tags(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    document_id UUID,
    user_id UUID
);

