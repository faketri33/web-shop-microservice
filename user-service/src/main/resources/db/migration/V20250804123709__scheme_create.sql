CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    keycloak_id VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    username VARCHAR(255) NOT NULL,
    images VARCHAR(255),
    create_at date
);

create table user_liked_product (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_id UUID REFERENCES users (id),
    product_id UUID
);

