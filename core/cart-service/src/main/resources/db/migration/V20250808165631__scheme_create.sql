CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE basket (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    price NUMERIC(19,2) NOT NULL
);

CREATE TABLE basket_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    basket_id UUID NOT NULL REFERENCES basket(id) ON DELETE CASCADE,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(19,2) NOT NULL
);