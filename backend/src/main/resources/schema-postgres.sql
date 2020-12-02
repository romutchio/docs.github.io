CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE documents(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    user_id UUID,
    data text
);

CREATE TABLE tags(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    document_id UUID,
    user_id UUID
);

